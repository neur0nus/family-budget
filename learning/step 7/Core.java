package game.test;

import java.util.Scanner;

public class Core {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Instructions.printWelcome();
		String userName = Instructions.askName(scanner);
        Instructions.printInstruction();
        // Выбор символа
        boolean userFirst = Instructions.symbolChoice(scanner);
        GameConfig config = new GameConfig(userName, userFirst);
    	//Инициализация поля и наполнение пробелами
		char[][] gameField = GameField.createField();
		char userSymbol = config.getUserSymbol();
        char computerSymbol = config.getComputerSymbol();
        System.out.println(config);  // Используем toString()
        System.out.println("Компьютер играет за " + computerSymbol);
        //Начало игры
        System.out.println("Для совершения хода напиши номер выбранной клетки:");
        for (int turn = 0; turn < 9; turn++) {
            boolean isUserTurn = userFirst ? (turn % 2 == 0) : (turn % 2 != 0);
            if (isUserTurn) { // Ход игрока
            	UserMove.makeUserMove(gameField, userSymbol, scanner);
            } else { // Ход компьютера
            	System.out.println("");
                System.out.println("Ход компьютера!");
                System.out.println("");
                ComputerMove.makeComputerMove(gameField, userSymbol, computerSymbol);
            }
            // Вывод поля
            GameField.printField(gameField);
            // Проверка победы
            int winner = CheckWin.checkWin(gameField, userSymbol, computerSymbol);
            if (winner == 1) { 
                System.out.println("Вы победили тупую машину!"); 
                break; 
            }
            if (winner == 2) { 
                System.out.println("Тупая машина победила!"); 
                break; 
            }
            if (turn == 8) {
                System.out.println("Ничья!");
            }
        }
        scanner.close();
    }
}
