package ru.improve.potato.core.validators.auth;

import org.springframework.validation.Errors;
import ru.improve.potato.api.dto.auth.LoginRequest;
import ru.improve.potato.core.validators.DefaultValidator;

public class AuthValidatorImp extends DefaultValidator implements AuthValidator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(LoginRequest.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        createAndThrowException(errors);
    }
}
