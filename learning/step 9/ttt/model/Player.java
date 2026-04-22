package ttt.model;

public abstract class Player {
    protected char symbol;
    protected String name;
    protected Board board;
    
    public Player(Board board, char symbol, String name) {
        this.board = board;
        this.symbol = symbol;
        this.name = name;
    }
    
    public abstract void doStep(Board board);
    
    public char getSymbol() {
        return symbol;
    }
    
    public String getName() {
        return name;
    }
}