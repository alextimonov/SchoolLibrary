package ua.timonov.aplib.exceptions;

/**
 * Exception that can be thrown if there is no item in database
 */
public class NoItemInDatabaseException extends RuntimeException {

    public NoItemInDatabaseException(String message) {
        super(message);
    }
}
