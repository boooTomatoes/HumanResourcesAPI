package controllers.rest.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IllegalSaveException extends RuntimeException{
    private String saveMessage;
    public IllegalSaveException(String message, String saveMessage) {
        super(message);
        this.saveMessage = saveMessage;
    }
}
