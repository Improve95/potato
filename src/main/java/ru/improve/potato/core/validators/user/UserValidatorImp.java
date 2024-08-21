package ru.improve.potato.core.validators.user;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import ru.improve.potato.api.dto.user.UserPatchRequest;
import ru.improve.potato.api.dto.auth.SingUpRequest;
import ru.improve.potato.core.validators.DefaultValidator;

@Component
public class UserValidatorImp extends DefaultValidator implements UserValidator {

    @Override
    public boolean supports(Class<?> clazz) {
        return (clazz.equals(SingUpRequest.class) ||
                clazz.equals(UserPatchRequest.class));
    }

    @Override
    public void validate(Object target, Errors errors) {
        createAndThrowException(errors);
    }
}
