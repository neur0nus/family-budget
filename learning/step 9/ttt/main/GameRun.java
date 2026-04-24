package ttt.main;

import ttt.model.Board;
import ttt.model.CellState;
import ttt.model.GameCore;
import ttt.model.CompPlayer;
import ttt.model.UserPlayer;
import ttt.model.Player;
import ttt.ui.Console;

public class GameRun {
    public static void main(String[] args) {
        Console ui = new Console();
        
        ui.printWelcome();
        ui.printInstruction();
        boolean userFirst = ui.askWhoFirst();
        
        Board board = new Board();
        
        Player human = new UserPlayer(board, userFirst ? CellState.X : CellState.ZERO, "Пользователь", ui);
        Player computer = new CompPlayer(board, userFirst ? CellState.ZERO : CellState.X, "Компьютер");
        
        GameCore game = new GameCore(board, human, computer, userFirst);
        game.run(ui);
        
        ui.close();
    }
}