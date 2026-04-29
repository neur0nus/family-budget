package ru.thread.hw;

public class ThreadHW {
		
	public static class Counter{
		private long counter = 0;
		public void inc() {this.counter++; };
		public long getCounter() {return this.counter; };
		
	}
	
	public static void main(String[] args) throws Throwable{
		
		Counter count = new Counter();
		final Object lock = new Object();
		
		Thread thr1 = new Thread(() -> { for (int i=0; i<5000; i++) {synchronized (lock) { count.inc();} System.out.println("Thread 1 "+count.getCounter());}});
		Thread thr2 = new Thread(() -> { for (int i=0; i<5000; i++) {synchronized (lock) { count.inc();}System.out.println("Thread 2 "+count.getCounter());}});
		thr1.start();
		thr2.start();
		thr1.join();
		thr2.join();
		System.out.println("Общий результат "+count.getCounter());
	}
	
}
