package cft.intensive.potato.core.exceptions.transfer;

public class NotEnoughtMoneyException extends RuntimeException {

    public NotEnoughtMoneyException() {
        super("not enought money");
    }

    public NotEnoughtMoneyException(String message) {
        super(message);
    }
}
