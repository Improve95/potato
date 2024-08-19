package ru.improve.potato.core.exceptions.user;

import ru.improve.potato.error.DefaultPotatoException;

import java.util.Set;

public class PersonOnCreateException extends DefaultPotatoException {

    public PersonOnCreateException(String message, Set<String> fieldsWithError) {
        super(message, fieldsWithError);
    }
}
