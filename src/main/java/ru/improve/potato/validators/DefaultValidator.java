package ru.improve.potato.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import ru.improve.potato.error.working.exceptions.OnCreateException;

import java.util.List;
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

            List<String> fieldsWithError = errors.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getField())
                    .collect(Collectors.toList());

            throw new OnCreateException(errorMsg.toString(), fieldsWithError);
        }
    }
}
