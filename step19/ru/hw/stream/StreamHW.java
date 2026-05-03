package ru.hw.stream;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
			order1.totalPrice = 100 + random.nextDouble()*10_000;
			order1.status = statuses[random.nextInt(statuses.length)];
			order.add(order1);
		}
		
		order.stream()
			//.filter(o -> o.totalPrice > 1000)
			.map(o -> "ID |"+o.id+"| Покупатель "+o.customerName+" Цена - "+o.totalPrice)
			.forEach(System.out::println);
	}
}