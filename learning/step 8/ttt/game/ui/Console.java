package ttt.game.ui;

import ttt.game.model.Board;
import java.util.Scanner;

public class Console {
    private Scanner scanner;
    
    public Console() {
        scanner = new Scanner(System.in);
    }
    
    public void printWelcome() {
        System.out.println("Привет! Поиграем в крестики нолики?");
    }
    
    public void printInstruction() {
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
    
    public void printBoard(Board board) {
        char[][] cells = board.getCells();
        System.out.println("---------------");
        System.out.println("| " + cells[0][0] + " || " + cells[0][1] + " || " + cells[0][2] + " |");
        System.out.println("---------------");
        System.out.println("| " + cells[1][0] + " || " + cells[1][1] + " || " + cells[1][2] + " |");
        System.out.println("---------------");
        System.out.println("| " + cells[2][0] + " || " + cells[2][1] + " || " + cells[2][2] + " |");
        System.out.println("---------------");
    }
    
    public String askName() {
        System.out.println("Как тебя зовут? ");
        System.out.println("Приятно познакомиться, " + scanner.nextLine());
        return scanner.nextLine();
    }
    
    public boolean askWhoFirst() {
        System.out.print("Хочешь ходить первым? (1 - да / 2 - нет): ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice == 1;
    }
    
    public int askMove() {
        System.out.print("Номер клетки: ");
        return scanner.nextInt();
    }
    
    public void showMessage(String msg) {
        System.out.println(msg);
    }
    
    public void showWinner(String name, char symbol) {
        System.out.println(name + " (" + symbol + ") победил!");
    }
    
    public void showDraw() {
        System.out.println("Ничья!");
        
    }
    
    public Scanner getScanner() {
        return scanner;
    }
    
    public void close() {
        scanner.close();
    }
   
}