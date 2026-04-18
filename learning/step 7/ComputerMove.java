package game.test;

public class ComputerMove {
	public static void makeComputerMove(char[][] gameField,char userSymbol, char computerSymbol) {
        boolean moveDone = false;
        // Приоритет ходов: центр, углы, оставшиеся клетки
        int[][] priority = {{1,1},{0,0},{0,2},{2,0},{2,2},{0,1},{1,0},{1,2},{2,1}};
        // Если есть выигрыш компьютера
        for (int[] line : GameField.winLines) {
            int r1=line[0],c1=line[1],r2=line[2],c2=line[3],r3=line[4],c3=line[5];
            int count = 0, emptyR = -1, emptyC = -1;
            // Подсчёт в трёх клетках линии
            char v1 = gameField[r1][c1], v2 = gameField[r2][c2], v3 = gameField[r3][c3];
            if (v1 == computerSymbol) count++; else if (v1 == ' ') { emptyR=r1; emptyC=c1; } else count = -1;
            if (count != -1) {
                if (v2 == computerSymbol) count++; else if (v2 == ' ') { emptyR=r2; emptyC=c2; } else count = -1;
            }
            if (count != -1) {
                if (v3 == computerSymbol) count++; else if (v3 == ' ') { emptyR=r3; emptyC=c3; } else count = -1;
            }
            if (count == 2 && emptyR != -1) {
                gameField[emptyR][emptyC] = computerSymbol;
                moveDone = true;
                break;
            }
        }
        // Блокировка игрока
        if (!moveDone) {
            for (int[] line : GameField.winLines) {
                int r1=line[0],c1=line[1],r2=line[2],c2=line[3],r3=line[4],c3=line[5];
                int count = 0, emptyR = -1, emptyC = -1;

                char v1 = gameField[r1][c1], v2 = gameField[r2][c2], v3 = gameField[r3][c3];
                if (v1 == userSymbol) count++; else if (v1 == ' ') { emptyR=r1; emptyC=c1; } else count = -1;
                if (count != -1) {
                    if (v2 == userSymbol) count++; else if (v2 == ' ') { emptyR=r2; emptyC=c2; } else count = -1;
                }
                if (count != -1) {
                    if (v3 == userSymbol) count++; else if (v3 == ' ') { emptyR=r3; emptyC=c3; } else count = -1;
                }
                if (count == 2 && emptyR != -1) {
                    gameField[emptyR][emptyC] = computerSymbol;
                    moveDone = true;
                    break;
                }
            }
        }
        // Приоритетный ход
        if (!moveDone) {
            for (int[] cell : priority) {
                if (gameField[cell[0]][cell[1]] == ' ') {
                    gameField[cell[0]][cell[1]] = computerSymbol;
                    break;
                }
            }
        }
	}
}
