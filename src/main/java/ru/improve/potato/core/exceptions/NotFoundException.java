package ru.improve.potato.core.exceptions;

import ru.improve.potato.error.DefaultPotatoException;

import java.util.List;
import java.util.stream.Collectors;

public class NotFoundException extends DefaultPotatoException {

    public NotFoundException(String message, List<String> fieldsWithError) {
        super(message, null);
        this.setFieldsWithError(fieldsWithError.stream().collect(Collectors.toSet()));
    }
}
