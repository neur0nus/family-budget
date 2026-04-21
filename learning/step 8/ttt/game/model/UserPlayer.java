package ttt.game.model;

import java.util.Scanner;

public class UserPlayer extends Player {
    
    public UserPlayer(char symbol, String name) {
       super (symbol, name) ;
    }
    
    @Override
    public void doStep(Board board, Scanner scanner) {
        while (true) {
            System.out.print(name + " (" + symbol + "), номер клетки (1-9): ");
            int move = scanner.nextInt();
            
            if (move < 1 || move > 9) {
                System.out.println("Ошибка! Число от 1 до 9.");
                continue;
            }
            
            int row = (move - 1) / 3;
            int col = (move - 1) % 3;
            
            if (!board.isCellEmpty(row, col)) {
                System.out.println("Ошибка! Клетка занята.");
                continue;
            }
            
            board.setCell(row, col, symbol);
            break;
        }
    }
}