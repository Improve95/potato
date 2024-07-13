package cft.intensive.potato.core.exceptions.user;

public class UserAlreadyExistException extends RuntimeException {
    private final int code;

    public UserAlreadyExistException(int code) {
        super("user already exist");
        this.code = code;
    }
}
