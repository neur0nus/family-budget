package ttt.model;

public class Validator implements IValidator {
    
    
    @Override
    public void validateName(String name) throws GameExceptions {
        if (name == null || name.length() == 0) {
            throw GameExceptions.emptyName();
        }
    }
    
    @Override
    public void validateYesNo(String input) throws GameExceptions {
        if (input == null || (!input.equals("1") && !input.equals("2"))) {
            throw GameExceptions.invalidChoice();
        }
    }
    
    @Override
    public void validateMove(String input, boolean[][] occupied) throws GameExceptions {
        int move;
        try {
        	move =
            // проверка на ввод числа;
        } catch (NumberFormatException err) {
            throw GameExceptions.notNumber();
        }
        
        if (move < 1 || move > 9) {
            throw GameExceptions.outOfRange();
        }
        
        int row = (move - 1) / 3;
        int col = (move - 1) % 3;
        if (occupied[row][col]) {
            throw GameExceptions.cellOccupied(move);
        }
    }
    
    @Override
    public void validateNumber(String input, int min, int max) throws GameExceptions {
        int number;
        try { number =
        	// проверка на ввод числа;
        } catch (NumberFormatException err) {
            throw GameExceptions.notNumber();
        }
        
        if (number < min || number > max) {
            throw GameExceptions.outOfRange();
        }
    }
}