package ttt.model;

public class Validator implements IValidator {
    
    @Override
    public void validateYesNo(int input) throws GameExceptions {
        if (input != 1 && input != 2) {
            throw GameExceptions.invalidChoice();
        }
    }
    
    @Override
    public void validateMove(int input, Board board) throws GameExceptions {
        if (input < 1 || input > 9) {
            throw GameExceptions.outOfRange();
        }
        int row = (input - 1) / 3;
        int col = (input - 1) % 3;
        if (!board.isCellEmpty(row, col)) {
            throw GameExceptions.cellOccupied(input);
        }
    }
}
