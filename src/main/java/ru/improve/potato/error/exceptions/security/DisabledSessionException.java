package ru.improve.potato.error.exceptions.security;

import org.springframework.security.core.AuthenticationException;

public class DisabledSessionException extends AuthenticationException {

    public DisabledSessionException() {
        super("session is disabled");
    }
}
