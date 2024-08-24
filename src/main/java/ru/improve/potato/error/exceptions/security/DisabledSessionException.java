package ru.improve.potato.error.exceptions.security;

public class DisabledSessionException extends RuntimeException {

    public DisabledSessionException() {
        super("session is disabled");
    }
}
