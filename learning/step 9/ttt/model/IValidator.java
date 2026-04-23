package ttt.model;

public interface IValidator {
	
    void validateName(String name) throws GameExceptions;
    
    void validateYesNo(String input) throws GameExceptions;
    
    void validateMove(String input, boolean[][] occupied) throws GameExceptions;
    
    void validateNumber(String input, int min, int max) throws GameExceptions;
}