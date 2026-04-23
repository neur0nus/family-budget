package ttt.model;

public enum CellState {

	  EMPTY(' '),
	  X('X'),
	  ZERO('0');
	    
	  private final char symbol;
	    
	  CellState(char s) {
	     this.symbol = s;
	  }
	    
	    public char getSymbol() {
	        return symbol;
	    }

}
