package game.test;

public class CheckWin {
	 public static int checkWin(char[][] gameField, char userSymbol, char computerSymbol) {
	        for (int[] line : GameField.winLines) {
	            int r1=line[0],c1=line[1],r2=line[2],c2=line[3],r3=line[4],c3=line[5];
	            if (gameField[r1][c1] == userSymbol && gameField[r2][c2] == userSymbol && gameField[r3][c3] == userSymbol) {
	            	return 1;
	            }
	            if (gameField[r1][c1] == computerSymbol && gameField[r2][c2] == computerSymbol && gameField[r3][c3] == computerSymbol) {
	            	return 2;
	            }
	        }
	        return 0;
	    }
}
