package ru.java.hw.parallelstream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ParallelStreamHW {

		public static class Person{
			public String name;
			public int age;
		}
		
		public static void main(String[] args) {
			
			List<Person> persons = new LinkedList<>();
			Random random = new Random();
	        String[] names = {"Иван", "Мария", "Петр", "Анна", "Сергей", "Елена", "Алексей", "Ольга"};
	        
	        for (int i = 0; i < 5_000_000; i++) {
	           
	        	Person person1 = new Person();
	        	person1.name = names[random.nextInt(names.length)];
	            person1.age = 18 + random.nextInt(62);
	            persons.add(person1);
	        }
	        

	        System.out.println("фильтрация по возрасту больше 30");
	        long start = System.nanoTime();
	        long filterSeqCount = persons.stream()
	            .filter(p -> p.age > 30)
	            .count();
	        long seqTime = (System.nanoTime() - start) / 1_000_000;
	        
	        start = System.nanoTime();
	        long filterParCount = persons.parallelStream()
	            .filter(p -> p.age > 30)
	            .count();
	        long parTime = (System.nanoTime() - start) / 1_000_000;
	        
	        System.out.println("Обычный стрим: " + seqTime + " мс");
	        System.out.println("Параллельный: " + parTime + " мс");
	        
	        System.out.println("сумма длин имен");
	        start = System.nanoTime();
	        int mapSeqSum = persons.stream()
	            .mapToInt(p -> p.name.toUpperCase().length())
	            .sum();
	        seqTime = (System.nanoTime() - start) / 1_000_000;
	        
	        start = System.nanoTime();
	        int mapParSum = persons.parallelStream()
	            .mapToInt(p -> p.name.toUpperCase().length())
	            .sum();
	        parTime = (System.nanoTime() - start) / 1_000_000;
	        
	        System.out.println("Обычный стрим: " + seqTime + " мс");
	        System.out.println("Параллельный: " + parTime + " мс");
	        

	        System.out.println("сумма возрастов");
	        start = System.nanoTime();
	        int sumSeq = persons.stream()
	            .mapToInt(p -> p.age)
	            .sum();
	        seqTime = (System.nanoTime() - start) / 1_000_000;
	        
	        start = System.nanoTime();
	        int sumPar = persons.parallelStream()
	            .mapToInt(p -> p.age)
	            .sum();
	        parTime = (System.nanoTime() - start) / 1_000_000;
	        
	        System.out.println("Обычный стрим: " + seqTime + " мс");
	        System.out.println("Параллельный: " + parTime + " мс");

	    }
	}
