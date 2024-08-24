package ru.improve.potato.validators.user;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import ru.improve.potato.dto.user.UserPatchRequest;
import ru.improve.potato.dto.user.UserPostRequest;
import ru.improve.potato.validators.DefaultValidator;

@Component
public class UserValidatorImp extends DefaultValidator implements UserValidator {

    @Override
    public boolean supports(Class<?> clazz) {
        return (clazz.equals(UserPostRequest.class) ||
                clazz.equals(UserPatchRequest.class));
    }

    @Override
    public void validate(Object target, Errors errors) {
        createAndThrowException(errors);
    }
}
