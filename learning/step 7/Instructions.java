package game.test;

import java.util.Scanner;

public class Instructions {
	
	public static void printWelcome() {
		System.out.println("Привет! Поиграем в крестики нолики?");
	}
	public static String askName(Scanner scanner) {
		System.out.println("Как тебя зовут?");
		return scanner.nextLine();
	}
	public static void printInstruction() {
        System.out.println("Поле игры представляет собой 9 клеток, размеченных номерами:");
	    System.out.println("---------------");
	    System.out.println("| 1 || 2 || 3 |");
	    System.out.println("---------------");
	    System.out.println("---------------");
	    System.out.println("| 4 || 5 || 6 |");
	    System.out.println("---------------");
	    System.out.println("---------------");
	    System.out.println("| 7 || 8 || 9 |");
	    System.out.println("---------------");
	    System.out.println("");
    }
	public static boolean symbolChoice(Scanner scanner) {
		System.out.println("Хочешь ходить первым? (1 - да / 2 - нет)");
		int choice = scanner.nextInt();
		return choice == 1;
	}
}