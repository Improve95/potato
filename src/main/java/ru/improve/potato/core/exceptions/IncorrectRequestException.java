package ru.improve.potato.core.exceptions;

public class IncorrectRequestException extends RuntimeException {
    public IncorrectRequestException(String message) {
        super(message);
    }

    public IncorrectRequestException() {
        super("incorrect parameters");
    }
}
