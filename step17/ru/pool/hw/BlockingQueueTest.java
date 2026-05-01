package ru.pool.hw;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BlockingQueueTest {
	
	private static final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);
	public static final ExecutorService pool = Executors.newFixedThreadPool(5);
	private static volatile boolean running = true;
	
	public static void main(String[] args) throws Exception {
		
		 for (int i = 1; i <= 2; i++) {
	            final int id = i;
	            pool.execute(() -> {
	                int num = 1;
	                while (running) {
	                    try {
	                        queue.put(num);
	                        System.out.println("Писатель - " + id + "добавил " + num 
	                            + " | очередь: " + queue.size() + "/2");
	                        num += 1;
	                        Thread.sleep(50);
	                    } catch (InterruptedException e) {
	                        break;
	                    }
	                }
	                System.out.println("Писатель-" + id + " остановлен");
	            });
	        }

		 for (int i = 1; i <= 3; i++) {
			 final int id = i;
			 pool.submit(() -> {
				 while (running) {
					 try {
						 int value = queue.take();
						 System.out.println("Читатель - " + id + "взял " + value 
								 + " | осталось: " + queue.size() + "/2");
						 Thread.sleep(300);
					 } catch (InterruptedException e) {
						 break;
					 }
				 }
				 System.out.println("Читатель-" + id + " остановлен");
			 });
		 }

		 for (int i = 5; i > 0; i--) {
			 System.out.println("Осталось " + i + " сек...");
			 Thread.sleep(1000);
		 }

		 System.out.println("Останавливаем потоки...");

		 pool.shutdownNow();
		 System.out.println("Осталось в очереди: " + queue.size());
	}
}
