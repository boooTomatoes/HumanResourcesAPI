package controllers.rest.exceptions;

public class IllegalDeleteException extends RuntimeException{
    public IllegalDeleteException(String message) {
        super(message);
    }
}
