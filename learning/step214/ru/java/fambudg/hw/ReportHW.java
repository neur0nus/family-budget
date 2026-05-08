package ru.java.fambudg.hw;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class ReportHW {

		public static void main(String[] args) {
	        // Генерируем данные
	        List<Transaction> transactions = new LinkedList<>();
	        Category[] categories = Category.values();
	        String[] currencies = {"USD", "EUR", "RUB"};
	        
	        for (int i = 0; i < 10_000_000; i++) {
	            Transaction t = new Transaction();
	            long daysAgo = ThreadLocalRandom.current().nextLong(0, 1095);
	            t.date = LocalDate.now().minusDays(daysAgo);
	            double amount = ThreadLocalRandom.current().nextDouble(0.01, 10000.00);
	            // 40% расходов
	            if (ThreadLocalRandom.current().nextDouble() < 0.4) {
	                amount = -amount;
	            }
	            t.amount = BigDecimal.valueOf(Math.round(amount * 100.0) / 100.0);
	            t.currency = currencies[ThreadLocalRandom.current().nextInt(currencies.length)];
	            t.category = categories[ThreadLocalRandom.current().nextInt(categories.length)];
	            t.description = "Transaction #" + (i + 1);
	            transactions.add(t);
	        }
	        
	        long start1 = System.nanoTime();
	        Map<Category, BigDecimal> result1 = transactions.stream()
	            .filter(t -> t.date.getYear() == 2025 && t.date.getMonth() == Month.MARCH)
	            .filter(t -> t.amount.compareTo(BigDecimal.ZERO) < 0)
	            .collect(Collectors.groupingBy(
	                Transaction::getCategory,
	                Collectors.reducing(BigDecimal.ZERO, Transaction::getAmount, BigDecimal::add)
	            ));
	        long time1 = (System.nanoTime() - start1) / 1_000_000;
	        
	        long start2 = System.nanoTime();
	        Map<Category, BigDecimal> result2 = transactions.parallelStream()
	            .filter(t -> t.date.getYear() == 2025 && t.date.getMonth() == Month.MARCH)
	            .filter(t -> t.amount.compareTo(BigDecimal.ZERO) < 0)
	            .collect(Collectors.groupingByConcurrent(
	                Transaction::getCategory,
	                Collectors.reducing(BigDecimal.ZERO, Transaction::getAmount, BigDecimal::add)
	            ));
	        long time2 = (System.nanoTime() - start2) / 1_000_000;
	        
	        System.out.println("Обычный stream:     " + time1 + " мс");
	        System.out.println("Parallel stream:    " + time2 + " мс");
	       
	    }
	}


