package ru.thread.hw;

public class ThreeThreads {

    public static void main(String[] args) {
        
        Thread nums = new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                System.out.println(" Число: " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        
        Thread symbols = new Thread(() -> {
            for (char c = 'A'; c < 'A' + 30; c++) {
                System.out.println(" Символ: " + c);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        
        Thread signs = new Thread(() -> {
            char[] signsArray = {'!', '@', '#', '$', '%', '^', '&', '*', '(', ')', 
                                 '-', '+', '=', '{', '}', '[', ']', '|', '\\', ';', 
                                 ':', '\'', '"', ',', '<', '.', '>', '/', '?', '~'};
            for (int i = 0; i < 30; i++) {
                System.out.println(" Знак: " + signsArray[i % signsArray.length]);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        nums.start();
        symbols.start();
        signs.start();
        try {
            nums.join();
            symbols.join();
            signs.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Все потоки завершили работу");
    }
}
