package ru.improve.potato.error.exceptions.security;

import ru.improve.potato.error.exceptions.CustomPotatoException;

import java.util.List;

public class IncorrectJwtTokenException extends CustomPotatoException {

    public IncorrectJwtTokenException(String message, List<String> fieldsWithError) {
        super(message, fieldsWithError);
    }
}
