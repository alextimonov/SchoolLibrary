package ua.timonov.aplib.exceptions;

/**
 * Created by Alex on 19.03.2017.
 */
public class ForbidToDeleteException extends RuntimeException {

    public ForbidToDeleteException(String message) {
        super(message);
    }
}
