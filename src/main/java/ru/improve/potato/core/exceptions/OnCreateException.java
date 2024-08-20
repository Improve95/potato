package ru.improve.potato.core.exceptions;

import ru.improve.potato.error.CustomPotatoException;

import java.util.List;

public class OnCreateException extends CustomPotatoException {

    public OnCreateException(String message, List<String> fieldsWithError) {
        super(message, fieldsWithError);
    }
}
