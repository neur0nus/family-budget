package ttt.model;

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
    
    public int getSize() {
        return size;
    }
    
    public static int[][] getPriority() {
        return priority;
    }
    public static int[][] getWinLines() {
        return winLines;
    }
}