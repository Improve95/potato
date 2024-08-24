package ru.improve.potato.error.exceptions;

import java.util.List;

public class NotFoundException extends CustomPotatoException {

    public NotFoundException(String message, List<String> fieldsWithError) {
        super(message, fieldsWithError);
    }
}
