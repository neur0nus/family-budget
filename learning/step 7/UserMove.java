package game.test;

import java.util.Scanner;

public class UserMove {
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
}
