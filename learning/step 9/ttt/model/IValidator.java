package ttt.model;

public interface IValidator {
	
    void validateYesNo(int input) throws GameExceptions;  
    void validateMove(int input, Board board) throws GameExceptions;

}