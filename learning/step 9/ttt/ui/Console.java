package ttt.ui;

import ttt.model.Board;
import ttt.model.CellState;
import ttt.model.Validator;
import ttt.model.GameExceptions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Console {
    private Scanner scanner;
    private Validator validator;
    
    public Console() {
        scanner = new Scanner(System.in);
        validator = new Validator(); 
    }
    
    public void printWelcome() {
        System.out.println("Привет! Поиграем в крестики нолики?");
    }
    
    public void printInstruction() {
        System.out.println("Поле игры представляет собой 9 клеток, размеченных номерами:");
        System.out.println("---------------");
        System.out.println("| 1 || 2 || 3 |");
        System.out.println("---------------");
        System.out.println("| 4 || 5 || 6 |");
        System.out.println("---------------");
        System.out.println("| 7 || 8 || 9 |");
        System.out.println("---------------");
        System.out.println();
    }
    
    public void printBoard(Board board) {
        CellState[][] cells = board.getCells();
        System.out.println("---------------");
        System.out.println("| " + cells[0][0].getSymbol() + " || " + cells[0][1].getSymbol() + " || " + cells[0][2].getSymbol() + " |");
        System.out.println("---------------");
        System.out.println("| " + cells[1][0].getSymbol() + " || " + cells[1][1].getSymbol() + " || " + cells[1][2].getSymbol() + " |");
        System.out.println("---------------");
        System.out.println("| " + cells[2][0].getSymbol() + " || " + cells[2][1].getSymbol() + " || " + cells[2][2].getSymbol() + " |");
        System.out.println("---------------");
    }
    
    public int[] convertToCoordinates(int move) {
        int row = (move - 1) / 3;
        int col = (move - 1) % 3;
        return new int[]{row, col};
    }
    
    public boolean askWhoFirst() {
        String playerSymbol = readPlayerSymbolFromFile();
    
        if (playerSymbol != null && !playerSymbol.isEmpty()) {
            System.out.println("Найден сохраненный символ игрока: " + playerSymbol);
            return playerSymbol.equals(String.valueOf(CellState.X.getSymbol())) || 
            	       playerSymbol.equals(String.valueOf(CellState.ZERO.getSymbol()));
        } else {
        	while (true) {
                System.out.print("Хочешь ходить первым? (1 - да / 2 - нет): ");
                if (scanner.hasNextInt()) {
                    int choice = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        validator.validateYesNo(choice);
                        return choice == 1;
                    } catch (GameExceptions e) {
                        System.out.println("Ошибка! " + e.getMessage());
                    }
                } else {
                    System.out.println("Ошибка! Введите число");
                    scanner.next();
                }
            }
        }
    }

    // Чтение файла
    private String readPlayerSymbolFromFile() {
    	File file = new File("/home/artem/Документы/family-budget/learning/step 9/symbol.txt"); 
        try {BufferedReader reader = new BufferedReader(new FileReader(file));
                String symbol = reader.readLine();
                reader.close();
                return symbol;
        } catch (IOException e) {
            System.out.println("Файл с сохраненным символом не найден.");
        }
        return null;
    }
    
    public int[] askMove(String playerName, char symbol, Board board) {
        while (true) {
            System.out.print(playerName + " (" + symbol + "), номер клетки (1-9): ");
            
            if (!scanner.hasNextInt()) {
                System.out.println("Ошибка! Введите число от 1 до 9");
                scanner.next();
                continue;
            }
            
            int move = scanner.nextInt();
            scanner.nextLine();
            
            try {
            	 validator.validateMove(move, board);
                 return convertToCoordinates(move);  
                
            } catch (GameExceptions e) {
                System.out.println("Ошибка! " + e.getMessage());
                if (e.getMessage().contains("занята")) {
                    printBoard(board);
                }
            }
        }
    }
    
    public void showMessage(String msg) {
        System.out.println(msg);
    }
    
    public void showWinner(String name, CellState symbol) {
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
