package ttt.game.main;

import ttt.game.model.Board;
import ttt.game.model.CompPlayer;
import ttt.game.model.UserPlayer;
import ttt.game.model.Player;
import ttt.game.ui.Console;

public class GameRun {
    public static void main(String[] args) {
        Console ui = new Console();
        
        // Приветствие
        ui.printWelcome();
        String userName = ui.askName();
        ui.printInstruction();
        boolean userFirst = ui.askWhoFirst();
        
        // Создание игроков (после переименования полей в Player)
        Player human = new UserPlayer(userFirst ? 'X' : '0', userName);
        Player computer = new CompPlayer(userFirst ? '0' : 'X', "Компьютер");
        
        // Создание доски
        Board board = new Board();
        
        ui.showMessage("\nИгра началась!\n");
        
        // Кто ходит первым
        Player currentPlayer = userFirst ? human : computer;
        
        // Игровой цикл
        while (true) {
            ui.printBoard(board);
            
            // Ход текущего игрока
            currentPlayer.doStep(board, ui.getScanner()); 
            
            // Проверка победы
            if (board.isWin(currentPlayer.getSymbol())) {
                ui.printBoard(board);
                ui.showWinner(currentPlayer.getName(), currentPlayer.getSymbol());
                break;
            }
            
            // Проверка ничьи
            if (board.isFull()) {
                ui.printBoard(board);
                ui.showDraw();
                break;
            }
            
            // Смена игрока
            currentPlayer = (currentPlayer == human) ? computer : human;
        }
        
        ui.close();
    }
}