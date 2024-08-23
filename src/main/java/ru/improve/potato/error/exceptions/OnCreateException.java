package ru.improve.potato.error.exceptions;

import java.util.List;

public class OnCreateException extends CustomPotatoException {

    public OnCreateException(String message, List<String> fieldsWithError) {
        super(message, fieldsWithError);
    }
}
