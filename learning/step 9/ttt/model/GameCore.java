package ttt.model;

import ttt.ui.Console;

public class GameCore {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private boolean isGameOver;
    private char winner;
    
    public GameCore(Board board, Player player1, Player player2, boolean player1First) {
        this.board = board; 
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1First ? player1 : player2;
        this.isGameOver = false;
        this.winner = ' ';
    }
    
    public boolean isFull() {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getCell(i, j) == ' ') return false;
            }
        }
        return true;
    }
    
    public boolean isWin(char symbol) {
        for (int[] line : Board.getWinLines()) {
            if (board.getCell(line[0], line[1]) == symbol &&
                board.getCell(line[2], line[3]) == symbol &&
                board.getCell(line[4], line[5]) == symbol) {
                return true;
            }
        }
        return false;
    }
    
    public void run(Console ui) {
        ui.showMessage("\nИгра началась!\n");
        
        while (!isGameOver) {
            ui.printBoard(board);
            currentPlayer.doStep(board);  // ← передаём board!
            
            if (isWin(currentPlayer.getSymbol())) {
                isGameOver = true;
                winner = currentPlayer.getSymbol();
                break;
            }
            
            if (isFull()) {
                isGameOver = true;
                winner = ' ';
                break;
            }
            
            switchPlayer();
        }
        
        ui.printBoard(board);
        if (winner != ' ') {
            String winnerName = (winner == player1.getSymbol()) ? player1.getName() : player2.getName();
            ui.showWinner(winnerName, winner);
        } else {
            ui.showDraw();
        }
    }
    
    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }
}