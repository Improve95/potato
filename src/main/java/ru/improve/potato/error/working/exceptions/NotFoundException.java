package ru.improve.potato.error.working.exceptions;

import ru.improve.potato.error.working.CustomPotatoException;

import java.util.List;

public class NotFoundException extends CustomPotatoException {

    public NotFoundException(String message, List<String> fieldsWithError) {
        super(message, fieldsWithError);
    }
}
