package ttt.model;

public class CompPlayer extends Player {
    
    public CompPlayer(Board board, CellState symbol, String name) {
        super(board, symbol, name);
    }
    
    @Override
    public void doStep() { 
        System.out.println("\n" + name + " думает...\n");
        
        // Выигрыш
        int[] move = findBestMove(board, symbol);
        if (move != null) {
            board.setCell(move[0], move[1], symbol);
            return;
        }
        
        // Блокировка
        CellState opponent = (symbol == CellState.X) ? CellState.ZERO : CellState.X;
        move = findBestMove(board, opponent);
        if (move != null) {
            board.setCell(move[0], move[1], symbol);
            return;
        }
        
        // Приоритет
        for (int[] cell : Board.getPriority()) {
            if (board.isCellEmpty(cell[0], cell[1])) {
                board.setCell(cell[0], cell[1], symbol);
                return;
            }
        }
    }
    
    private int[] findBestMove(Board board, CellState target) { 
        for (int[] line : Board.getWinLines()) {
            int count = 0;
            int emptyRow = -1, emptyCol = -1;
            
            for (int i = 0; i < 6; i += 2) {
                int row = line[i];
                int col = line[i + 1];
                CellState cell = board.getCell(row, col);
                
                if (cell == target) {
                    count++;
                } else if (cell == 	CellState.EMPTY) {
                    emptyRow = row;
                    emptyCol = col;
                } else {
                    count = -1;
                    break;
                }
            }
            
            if (count == 2 && emptyRow != -1) {
                return new int[]{emptyRow, emptyCol};
            }
        }
        return null;
    }
}