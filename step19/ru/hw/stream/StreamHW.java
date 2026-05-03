package ru.hw.stream;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;


public class StreamHW {
	
	public static enum ORDER_STATUS {NEW, PAID, SHIPPED, DELIVERED}
	
	public static class Order {
		public String id;
		public String customerName;
		public double totalPrice;
		public ORDER_STATUS status;
	}
	
	public static void main(String[] args) {
		List<Order> order = new LinkedList<>();
		
		Random random = new Random();
		String[] names = {"Иван", "Мария", "Петр", "Анна", "Сергей", "Елена", "Алексей", "Ольга"};
		ORDER_STATUS[] statuses = ORDER_STATUS.values();
		
		for (int i = 0; i<10; i++) {
			Order order1 = new Order();
			order1.id = UUID.randomUUID().toString();
			order1.customerName = names[random.nextInt(names.length)];
			order1.totalPrice = random.nextDouble()*10_000;
			order1.status = statuses[random.nextInt(statuses.length)];
			order.add(order1);
		}
		
		List<String> overth = order.stream()
			.filter(o -> o.totalPrice > 1000)
			.map(o -> "\nID |"+o.id+"| Покупатель "+o.customerName+" Цена - "+o.totalPrice)
			.collect(Collectors.toList());
		System.out.println(overth);
		
		List<Order> paidOrd = order.stream()
			    .filter(o -> o.status == ORDER_STATUS.PAID)     
			    .filter(o -> "Мария".equals(o.customerName))          
			    .sorted(Comparator.comparingDouble(o -> o.totalPrice))  
			    .collect(Collectors.toList());
		System.out.println(paidOrd);		
		
		System.out.println(order.stream()
			.map(o -> o.totalPrice)
			.reduce(0.0, Double::sum)); 
	}
}