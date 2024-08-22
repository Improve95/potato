package ru.improve.potato.error.working.exceptions;

import ru.improve.potato.error.working.CustomPotatoException;

import java.util.List;

public class OnCreateException extends CustomPotatoException {

    public OnCreateException(String message, List<String> fieldsWithError) {
        super(message, fieldsWithError);
    }
}
