package cft.intensive.potato.core.exceptions;

public class IncorrectRequestException extends RuntimeException {

    private final int code;

    public IncorrectRequestException(String message, int code) {
        super(message);
        this.code = code;
    }

    public IncorrectRequestException(int code) {
        super("incorrect parameters");
        this.code = code;
    }
}
