package ru.improve.potato.error.exceptions;

public class DisabledSessionException extends RuntimeException {

    public DisabledSessionException() {
        super("session is disabled");
    }
}
