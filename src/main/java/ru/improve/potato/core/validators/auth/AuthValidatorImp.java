package ru.improve.potato.core.validators.auth;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import ru.improve.potato.api.dto.auth.LoginRequest;
import ru.improve.potato.api.dto.auth.SingUpRequest;
import ru.improve.potato.core.validators.DefaultValidator;

@Component
public class AuthValidatorImp extends DefaultValidator implements AuthValidator {

    @Override
    public boolean supports(Class<?> clazz) {
        return (clazz.equals(LoginRequest.class) ||
                clazz.equals(SingUpRequest.class));
    }

    @Override
    public void validate(Object target, Errors errors) {
        createAndThrowException(errors);
    }
}
