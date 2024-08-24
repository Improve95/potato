package ru.improve.potato.error.exceptions.security;

public class ExpiredSessionException extends RuntimeException {

    public ExpiredSessionException(String message) {
        super(message);
    }
}
