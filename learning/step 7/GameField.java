package game.test;

public class GameField {
	public static final int[][] winLines = {
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
		public static char[][] createField() {
			char[][] gameField = new char[3][3];
	        for (int i = 0; i < 3; i++) {
	            for (int j = 0; j < 3; j++) {
	                gameField[i][j] = ' ';
	            }
	        }
	        return gameField;
		}
		public static void printField(char[][] gameField) {
	        System.out.println("---------------");
	        System.out.println("| " + gameField[0][0] + " || " + gameField[0][1] + " || " + gameField[0][2] + " |");
	        System.out.println("---------------");
	        System.out.println("| " + gameField[1][0] + " || " + gameField[1][1] + " || " + gameField[1][2] + " |");
	        System.out.println("---------------");
	        System.out.println("| " + gameField[2][0] + " || " + gameField[2][1] + " || " + gameField[2][2] + " |");
	        System.out.println("---------------");
		}
}
