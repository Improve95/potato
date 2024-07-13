package cft.intensive.potato.core.exceptions.transfer;

public class NotEnoughtMoneyException extends RuntimeException {

    private final int code;

    public NotEnoughtMoneyException(int code) {
        super("not enought money");
        this.code = code;
    }

    public NotEnoughtMoneyException(String message, int code) {
        super(message);
        this.code = code;
    }
}
