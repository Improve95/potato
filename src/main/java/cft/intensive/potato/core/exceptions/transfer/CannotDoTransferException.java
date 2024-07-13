package cft.intensive.potato.core.exceptions.transfer;

public class CannotDoTransferException extends RuntimeException {
    private final int code;

    public CannotDoTransferException(int code) {
        super("cannot do transfer");
        this.code = code;
    }

    public CannotDoTransferException(String message, int code) {
        super(message);
        this.code = code;
    }
}
