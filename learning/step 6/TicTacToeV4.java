package ru.java.lessons.test;

import java.util.Scanner;

public class TicTacToeV4 {
	
	static final int[][] winLines = {
        // Все выигрышные линии
            {0,0,0,1,0,2}, // горизонтали
            {1,0,1,1,1,2},
            {2,0,2,1,2,2},
            {0,0,1,0,2,0}, // вертикали
            {0,1,1,1,2,1},
            {0,2,1,2,2,2},
            {0,0,1,1,2,2}, // диагонали
            {0,2,1,1,2,0}
	};
	public static char[][] createGameField() {
		char[][] gameField = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameField[i][j] = ' ';
            }
        }
        return gameField;
	}
	public static void printField() {
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
	public static void printCurrentField(char[][] gameField) {
        System.out.println("---------------");
        System.out.println("| " + gameField[0][0] + " || " + gameField[0][1] + " || " + gameField[0][2] + " |");
        System.out.println("---------------");
        System.out.println("| " + gameField[1][0] + " || " + gameField[1][1] + " || " + gameField[1][2] + " |");
        System.out.println("---------------");
        System.out.println("| " + gameField[2][0] + " || " + gameField[2][1] + " || " + gameField[2][2] + " |");
        System.out.println("---------------");
	}
	public static void makeUserMove(char[][] gameField, char userSymbol, Scanner scanner) {
        while (true) {
            System.out.print("Твой ход. Напиши номер клетки: ");
            int move = scanner.nextInt();
            if (move < 1 || move > 9) {
                System.out.println("Ошибка! Введите число от 1 до 9.");
                continue;
            }
            int r = (move-1)/3, c = (move-1)%3;
            if (gameField[r][c] != ' ') {
                System.out.println("Ошибка! Клетка занята.");
                continue;
            }
            gameField[r][c] = userSymbol;
            break;
        }
	}
	public static void makeComputerMove(char[][] gameField,char userSymbol, char computerSymbol) {
        boolean moveDone = false;
        // Приоритет ходов: центр, углы, оставшиеся клетки
        int[][] priority = {{1,1},{0,0},{0,2},{2,0},{2,2},{0,1},{1,0},{1,2},{2,1}};
        // Если есть выигрыш компьютера
        for (int[] line : winLines) {
            int r1=line[0],c1=line[1],r2=line[2],c2=line[3],r3=line[4],c3=line[5];
            int count = 0, emptyR = -1, emptyC = -1;
            // Подсчёт в трёх клетках линии
            char v1 = gameField[r1][c1], v2 = gameField[r2][c2], v3 = gameField[r3][c3];
            if (v1 == computerSymbol) count++; else if (v1 == ' ') { emptyR=r1; emptyC=c1; } else count = -1;
            if (count != -1) {
                if (v2 == computerSymbol) count++; else if (v2 == ' ') { emptyR=r2; emptyC=c2; } else count = -1;
            }
            if (count != -1) {
                if (v3 == computerSymbol) count++; else if (v3 == ' ') { emptyR=r3; emptyC=c3; } else count = -1;
            }
            if (count == 2 && emptyR != -1) {
                gameField[emptyR][emptyC] = computerSymbol;
                moveDone = true;
                break;
            }
        }
        // Блокировка игрока
        if (!moveDone) {
            for (int[] line : winLines) {
                int r1=line[0],c1=line[1],r2=line[2],c2=line[3],r3=line[4],c3=line[5];
                int count = 0, emptyR = -1, emptyC = -1;

                char v1 = gameField[r1][c1], v2 = gameField[r2][c2], v3 = gameField[r3][c3];
                if (v1 == userSymbol) count++; else if (v1 == ' ') { emptyR=r1; emptyC=c1; } else count = -1;
                if (count != -1) {
                    if (v2 == userSymbol) count++; else if (v2 == ' ') { emptyR=r2; emptyC=c2; } else count = -1;
                }
                if (count != -1) {
                    if (v3 == userSymbol) count++; else if (v3 == ' ') { emptyR=r3; emptyC=c3; } else count = -1;
                }
                if (count == 2 && emptyR != -1) {
                    gameField[emptyR][emptyC] = computerSymbol;
                    moveDone = true;
                    break;
                }
            }
        }
        // Приоритетный ход
        if (!moveDone) {
            for (int[] cell : priority) {
                if (gameField[cell[0]][cell[1]] == ' ') {
                    gameField[cell[0]][cell[1]] = computerSymbol;
                    break;
                }
            }
        }
	}
    public static int checkWinner(char[][] gameField, char userSymbol, char computerSymbol) {
        for (int[] line : winLines) {
            int r1=line[0],c1=line[1],r2=line[2],c2=line[3],r3=line[4],c3=line[5];
            if (gameField[r1][c1] == userSymbol && gameField[r2][c2] == userSymbol && gameField[r3][c3] == userSymbol) {
            	return 1;
            }
            if (gameField[r1][c1] == computerSymbol && gameField[r2][c2] == computerSymbol && gameField[r3][c3] == computerSymbol) {
            	return 2;
            }
        }
        return 0;
    }
    public static void main(String[] args) {
    	//Инициализация поля и наполнение пробелами
		char[][] gameField = createGameField();
		//Приветствие игрока и начало игры
        Scanner scanner = new Scanner(System.in);
        System.out.println("Привет! Поиграем в крестики нолики?");
        System.out.println("Поле игры представляет собой 9 клеток, размеченных номерами:");
        printField();
        System.out.println("Хочешь ходить первым? (1 - да / 2 - нет)");
        int choice = scanner.nextInt();
        boolean userFirst = (choice == 1);
        //Определение символов игрока и компьютера
        char userSymbol = userFirst ? 'X' : '0';
        char computerSymbol = userFirst ? '0' : 'X';
        System.out.println("Ты ходишь " + userSymbol + ". Компьютер ходит " + computerSymbol + ".");
        System.out.println("Для совершения хода напиши номер выбранной клетки:");
        //
        for (int turn = 0; turn < 9; turn++) {
            boolean isUserTurn = userFirst ? (turn % 2 == 0) : (turn % 2 != 0);
            if (isUserTurn) { // Ход игрока
            	makeUserMove(gameField, userSymbol, scanner);
            } else { // Ход компьютера
            	System.out.println("");
                System.out.println("Ход компьютера!");
                System.out.println("");
                makeComputerMove(gameField, userSymbol, computerSymbol);
            }
            // Вывод поля
            printCurrentField(gameField);
            // Проверка победы
            int winner = checkWinner(gameField, userSymbol, computerSymbol);
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
