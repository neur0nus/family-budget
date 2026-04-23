package ttt.model;

public abstract class Player {
    protected CellState symbol;
    protected String name;
    protected Board board;
    
    public Player(Board board, CellState symbol, String name) {
        this.board = board;
        this.symbol = symbol;
        this.name = name;
    }
    
    public abstract void doStep();
    
    public CellState getSymbol() {
        return symbol;
    }
    
    public String getName() {
        return name;
    }
}