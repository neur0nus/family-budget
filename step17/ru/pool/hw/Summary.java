package ru.pool.hw;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Summary {
    
    public static final int arrSize = 1_000_000;
    public static final int taskCount = 100;

    public static long flowSummarizer(long[] array, int start, int end) {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += array[i];
        }
        return sum;
    }
    
    public static void main(String[] args) throws InterruptedException {
        
        long[] array = new long[arrSize];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextLong();
        }
        
        long startSeq = System.currentTimeMillis();
        long sequentialSum = flowSummarizer(array, 0, arrSize);
        long endSeq = System.currentTimeMillis();
        System.out.println("---Последовательное выяисление");
        System.out.println("Результат: " + sequentialSum);
        System.out.println("Время: " + (endSeq - startSeq) + " мс\n");
        
        long startPar = System.currentTimeMillis();
        
        ExecutorService executor = Executors.newFixedThreadPool(taskCount);
        
        Future<Long>[] futures = new Future[taskCount];
        int chunkSize = arrSize / taskCount;
        
        for (int i = 0; i < taskCount; i++) {
            final int start = i * chunkSize;
            final int end =(i + 1) * chunkSize;
            
            Callable<Long> task = () -> flowSummarizer(array, start, end);
            futures[i] = executor.submit(task);
        }
        
        long parallelSum = 0;
        for (Future<Long> future : futures) {
            try {
				parallelSum += future.get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        executor.shutdown();
        
        long endPar = System.currentTimeMillis();
        
        System.out.println("---Поточное вычисление");
        System.out.println("Результат: " + parallelSum);
        System.out.println("Время: " + (endPar - startPar) + " мс");
        System.out.println("Количество потоков: " + taskCount);
        System.out.println("Сходимость результатов " + (parallelSum==sequentialSum));
    }
}
