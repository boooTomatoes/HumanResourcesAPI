package controllers.rest.exceptions;

public class IllegalSaveException extends RuntimeException{
    public IllegalSaveException(String message) {
        super(message);
    }
}
