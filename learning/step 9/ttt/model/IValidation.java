package ttt.model;

public interface IValidation {
	
	  boolean isValid(String input);

	    // Сообщение об ошибке
	    String getErrorMessage();

}
