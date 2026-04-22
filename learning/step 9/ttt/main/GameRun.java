package ttt.main;

import ttt.model.Board;
import ttt.model.GameCore;
import ttt.model.CompPlayer;
import ttt.model.UserPlayer;
import ttt.model.Player;
import ttt.ui.Console;

public class GameRun {
    public static void main(String[] args) {
        Console ui = new Console();
        
        ui.printWelcome();
        String userName = ui.askName();
        ui.printInstruction();
        boolean userFirst = ui.askWhoFirst();
        
        Board board = new Board();
        
        Player human = new UserPlayer(board, userFirst ? 'X' : '0', userName, ui);
        Player computer = new CompPlayer(board, userFirst ? '0' : 'X', "Компьютер");
        
        GameCore game = new GameCore(board, human, computer, userFirst);
        game.run(ui);
        
        ui.close();
    }
}