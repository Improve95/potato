package ru.improve.potato.core.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import ru.improve.potato.core.exceptions.user.PersonOnCreateException;

import java.util.Set;
import java.util.stream.Collectors;

public abstract class DefaultValidator {

    protected void createAndThrowException(Errors errors) {
        if (errors.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            for (FieldError error : errors.getFieldErrors()) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append("; ");
            }

            Set<String> fieldsWithError = errors.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getField())
                    .collect(Collectors.toSet());

            throw new PersonOnCreateException(errorMsg.toString(), fieldsWithError);
        }
    }
}
