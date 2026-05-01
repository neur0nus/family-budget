package ru.java.hw;

public class Counter {
	
	private static int count = 0;
	private volatile static boolean run = true;
	
	public static void main(String[] args) throws Exception {
		
		Thread counter = new Thread(() -> {
			System.out.println("Счетчик запущен");
			while (run) {
				count++;
				if (count == 10000000) {
					run = false;
					System.out.println("Счетчик остановлен на конечном значении "+count);
				}
			}
			
		});
		
		Thread flag = new Thread (() -> {
			System.out.println("Останавливаю счетчик");
			try {
				Thread.sleep(2);
				run = false;
				System.out.println("Счетчик остановлен флагом на значении "+count);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		counter.start();
		flag.start();
		counter.join();
		flag.join();
	}

}
