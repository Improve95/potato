package ru.improve.potato.exceptions;

import ru.improve.potato.error.CustomPotatoException;

import java.util.List;

public class NotFoundException extends CustomPotatoException {

    public NotFoundException(String message, List<String> fieldsWithError) {
        super(message, fieldsWithError);
    }
}
