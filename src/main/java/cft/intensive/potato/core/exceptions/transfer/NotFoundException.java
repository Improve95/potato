package cft.intensive.potato.core.exceptions.transfer;

public class NotFoundException extends RuntimeException {

    private final int code;

    public NotFoundException(int code) {
        super("not found object");
        this.code = code;
    }

    public NotFoundException(String message, int code) {
        super(message);
        this.code = code;
    }
}
