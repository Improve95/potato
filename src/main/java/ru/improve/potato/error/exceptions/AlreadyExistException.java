package ru.improve.potato.error.exceptions;

import ru.improve.potato.error.CustomPotatoException;

import java.util.List;

public class AlreadyExistException extends CustomPotatoException {

    public AlreadyExistException(String message, List<String> fieldsWithError) {
        super(message, fieldsWithError);
    }
}
