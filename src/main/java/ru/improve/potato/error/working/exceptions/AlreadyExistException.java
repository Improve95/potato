package ru.improve.potato.error.working.exceptions;

import ru.improve.potato.error.working.CustomPotatoException;

import java.util.List;

public class AlreadyExistException extends CustomPotatoException {

    public AlreadyExistException(String message, List<String> fieldsWithError) {
        super(message, fieldsWithError);
    }
}
