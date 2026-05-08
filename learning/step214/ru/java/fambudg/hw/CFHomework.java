package ru.java.fambudg.hw;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CFHomework {
    
    private static final List<Transaction> transactions = new LinkedList<>();
    
    public static void addTransaction(Transaction tx) {
        if (tx == null) return;   
        if (!"RUB".equalsIgnoreCase(tx.currency)) {
            BigDecimal rate = fetchRateFromAPI(tx.currency);
            tx.amount = tx.amount.multiply(rate);
            tx.currency = "RUB";
        }
        transactions.add(tx);
    }
    
    public static CompletableFuture<Transaction> addTransactionAsync(Transaction tx) {
        long startTime = System.currentTimeMillis();
        
        return CompletableFuture.supplyAsync(() -> {
            if ("RUB".equalsIgnoreCase(tx.currency)) return tx;
            BigDecimal rate = fetchRateFromAPI(tx.currency);
            tx.amount = tx.amount.multiply(rate);
            tx.currency = "RUB";
            return tx;
        }).thenApplyAsync(converted -> {
            synchronized (transactions) {
                transactions.add(converted);
            }
            long time = System.currentTimeMillis() - startTime;
            return converted;
        }).exceptionally(err -> {
            System.err.println("Ошибка: " + err.getMessage());
            return tx;
        });
    }
    
    private static BigDecimal fetchRateFromAPI(String currency) {
        try {
            Thread.sleep(300); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new BigDecimal("1000.555");
    }
    
    public static void main(String[] args) {

        transactions.clear();
        long startSync = System.currentTimeMillis();
        
        for (int i = 0; i < 5; i++) {
            Transaction tx = new Transaction();
            tx.amount = new BigDecimal("100500");
            tx.currency = "USD";
            addTransaction(tx);
        }
        
        long syncTime = System.currentTimeMillis() - startSync;
        transactions.clear();

        long startAsync = System.currentTimeMillis();
        
        List<CompletableFuture<Transaction>> futures = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            Transaction tx = new Transaction();
            tx.amount = new BigDecimal("100500");
            tx.currency = "USD";
            CompletableFuture<Transaction> cf = addTransactionAsync(tx);
            futures.add(cf);
        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        
        long asyncTime = System.currentTimeMillis() - startAsync;
        System.out.println("   Синхронная обработка:   " + syncTime + " мс");
        System.out.println("   Асинхронная обработка:  " + asyncTime + " мс");

        }
    }