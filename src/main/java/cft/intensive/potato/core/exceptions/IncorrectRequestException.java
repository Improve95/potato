package cft.intensive.potato.core.exceptions;

public class IncorrectRequestException extends RuntimeException {
    public IncorrectRequestException(String message, int code) {
        super(message);
    }

    public IncorrectRequestException() {
        super("incorrect parameters");
    }
}
