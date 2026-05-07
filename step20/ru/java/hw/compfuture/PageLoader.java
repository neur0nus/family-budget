package ru.java.hw.compfuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class PageLoader {
    
    public static void main(String[] args) {
        
        CompletableFuture<String> page1 = CompletableFuture.supplyAsync(() -> {
            sleep(1000);
            return "[HTML страницы 1]";
        });
        CompletableFuture<String> page2 = CompletableFuture.supplyAsync(() -> {
            sleep(2000);
            return "[HTML страницы 2]";
        });
        CompletableFuture<String> page3 = CompletableFuture.supplyAsync(() -> {
            sleep(1500);
            throw new RuntimeException("Страница 3 не загрузилась!");
        });
        
        CompletableFuture
            .allOf(page1, page2, page3)
            .thenApply(v -> {
                try {
                    return page1.get() + "\n" + page2.get() + "\n" + page3.get();
                } catch (Exception e) {
                    return "Ошибка: одна из страниц не загрузилась - " + e.getMessage();
                }
            })
            .exceptionally(ex -> {
                return "Ошибка: " + ex.getMessage();
            })
            .thenAccept(System.out::println);
        
        sleep(3500);
    }
    
    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}


