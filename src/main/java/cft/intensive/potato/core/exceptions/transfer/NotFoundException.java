package cft.intensive.potato.core.exceptions.transfer;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("not found object");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
