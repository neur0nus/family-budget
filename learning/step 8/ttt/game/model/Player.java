package ttt.game.model;

import java.util.Scanner;

public abstract class Player {
	
	protected char symbol;
	protected String name;
	
	public Player(char ch, String name) {
		this.symbol = ch;
		this.name = name;
	}
	
	public abstract void doStep(Board board, Scanner scanner);
	
	public char getSymbol() {
	    return symbol;
	}

	public String getName() {
	    return name;
	}
}
