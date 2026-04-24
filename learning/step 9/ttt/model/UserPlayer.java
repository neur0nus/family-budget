package ttt.model;

import ttt.ui.Console;

public class UserPlayer extends Player {
    private Console ui;
    
    public UserPlayer(Board board, CellState c, String name, Console ui) {
        super(board, c, name);
        this.ui = ui;
    }
    
    @Override
    public void doStep() { 
        int[] move = ui.askMove(name, symbol.getSymbol(), board);
        int row = move[0];  
        int col = move[1]; 
        board.setCell(row, col, symbol);
    }
}