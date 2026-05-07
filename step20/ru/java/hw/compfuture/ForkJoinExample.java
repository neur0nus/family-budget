package ru.java.hw.compfuture;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Arrays;

public class ForkJoinExample {
    private static final int THRESHOLD = 1_000_000;
    
    static class SumSquaresTask extends RecursiveTask<Long> {
        private final int[] array;
        private final int start;
        private final int end;
        
        public SumSquaresTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }
        
        @Override
        protected Long compute() {
            if (end - start <= THRESHOLD) {
                long sum = 0;
                for (int i = start; i < end; i++) {
                    sum += (long) array[i] * array[i];
                }
                return sum;
            }
            
            int mid = (start + end) / 2;
            
            SumSquaresTask leftTask = new SumSquaresTask(array, start, mid);
            SumSquaresTask rightTask = new SumSquaresTask(array, mid, end);
            
            leftTask.fork();
            Long rightResult = rightTask.compute();
            Long leftResult = leftTask.join();
            return leftResult + rightResult;
        }
    }
    
    static class FindMaxTask extends RecursiveTask<Integer> {
        private final int[] array;
        private final int start;
        private final int end;
        
        public FindMaxTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }
        
        @Override
        protected Integer compute() {
            if (end - start <= THRESHOLD) {
                int max = Integer.MIN_VALUE;
                for (int i = start; i < end; i++) {
                    if (array[i] > max) {
                        max = array[i];
                    }
                }
                return max;
            }
            
            int mid = (start + end) / 2;
            
            FindMaxTask leftTask = new FindMaxTask(array, start, mid);
            FindMaxTask rightTask = new FindMaxTask(array, mid, end);

            leftTask.fork();
            rightTask.fork();
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();
            return Math.max(leftResult, rightResult);
        }
    }
    
    public static void main(String[] args) {
        int size = 10_000_000;
        int[] array = new int[size];
        
        System.out.println("Заполнение массива из " + size + " элементов...");
        for (int i = 0; i < size; i++) {
            array[i] = ThreadLocalRandom.current().nextInt(1001);
        }
        ForkJoinPool pool = new ForkJoinPool();
        
        long startSequential = System.nanoTime();
        long sequentialSumSquares = sequentialSumSquares(array);
        long endSequential = System.nanoTime();
        long sequentialTime = endSequential - startSequential;
        
        System.out.println("Последовательный расчёт: " + sequentialSumSquares);
        System.out.println("Время: " + sequentialTime / 1_000_000 + " мс");
     
        long startParallel = System.nanoTime();
        SumSquaresTask sumTask = new SumSquaresTask(array, 0, array.length);
        long parallelSumSquares = pool.invoke(sumTask);
        long endParallel = System.nanoTime();
        long parallelTime = endParallel - startParallel;
        
        System.out.println("Параллельный расчёт: " + parallelSumSquares);
        System.out.println("Время: " + parallelTime / 1_000_000 + " мс");
        
        startSequential = System.nanoTime();
        int sequentialMax = sequentialFindMax(array);
        endSequential = System.nanoTime();
        sequentialTime = endSequential - startSequential;
        
        System.out.println("Последовательный поиск: max = " + sequentialMax);
        System.out.println("Время: " + sequentialTime / 1_000_000 + " мс");
        
        startParallel = System.nanoTime();
        FindMaxTask maxTask = new FindMaxTask(array, 0, array.length);
        int parallelMax = pool.invoke(maxTask);
        endParallel = System.nanoTime();
        parallelTime = endParallel - startParallel;
        
        System.out.println("Параллельный поиск : max = " + parallelMax);
        System.out.println("Время: " + parallelTime / 1_000_000 + " мс");
        
        pool.shutdown();
    }
    
    private static long sequentialSumSquares(int[] array) {
        long sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += (long) array[i] * array[i];
        }
        return sum;
    }
    
    private static int sequentialFindMax(int[] array) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }
}