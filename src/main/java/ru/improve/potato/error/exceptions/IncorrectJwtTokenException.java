package ru.improve.potato.error.exceptions;

import java.util.List;

public class IncorrectJwtTokenException extends CustomPotatoException {

    public IncorrectJwtTokenException(String message, List<String> fieldsWithError) {
        super(message, fieldsWithError);
    }
}
