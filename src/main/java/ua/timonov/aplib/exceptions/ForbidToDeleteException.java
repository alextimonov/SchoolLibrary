package ua.timonov.aplib.exceptions;

/**
 * Created by Alex on 19.03.2017.
 */
public class ForbidToDeleteException extends RuntimeException {
    private String nestedMessage;

    public String getNestedMessage() {
        return nestedMessage;
    }

    public ForbidToDeleteException(String message, String nestedMessage) {
        super(message);
        this.nestedMessage = nestedMessage;
    }
}
