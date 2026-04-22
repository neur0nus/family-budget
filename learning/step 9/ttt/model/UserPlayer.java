package ttt.model;

import ttt.ui.Console;

public class UserPlayer extends Player {
    private Console ui;
    
    public UserPlayer(Board board, char symbol, String name, Console ui) {
        super(board, symbol, name);
        this.ui = ui;
    }
    
    @Override
    public void doStep(Board board) { 
        int[] move = ui.askMove(name, symbol, board);
        int row = move[0];  
        int col = move[1]; 
        board.setCell(row, col, symbol);
    }
}