package ttt.game.model;

public class Board {
    private char[][] cells;
    private static final int size = 3;
    // Выигрышные линии
    private static final int[][] winLines = {
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
    
    // Приоритет ходов
    private static final int[][] priority = {
        {1,1}, {0,0}, {0,2}, {2,0}, {2,2},
        {0,1}, {1,0}, {1,2}, {2,1}
    };
    
    public Board() {
        cells = new char[size][size];
        clear();
    }
    
    public void clear() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = ' ';
            }
        }
    }
    
    public boolean isCellEmpty(int row, int col) {
        return cells[row][col] == ' ';
    }
    
    public void setCell(int row, int col, char symbol) {
        cells[row][col] = symbol;
    }
    
    public char getCell(int row, int col) {
        return cells[row][col];
    }
    
    public char[][] getCells() {
        return cells;
    }
    
    public boolean isFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (cells[i][j] == ' ') return false;
            }
        }
        return true;
    }
    public boolean isWin(char symbol) {
        for (int[] line : winLines) {
            if (getCell(line[0], line[1]) == symbol &&
                getCell(line[2], line[3]) == symbol &&
                getCell(line[4], line[5]) == symbol) {
                return true;
            }
        }
        return false;
    }
    public static int[][] getPriority() {
        return priority;
    }
    public static int[][] getWinLines() {
        return winLines;
    }
}