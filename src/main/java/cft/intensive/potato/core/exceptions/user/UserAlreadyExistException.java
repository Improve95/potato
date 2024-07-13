package cft.intensive.potato.core.exceptions.user;

public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException() {
        super("user already exist");
    }
}
