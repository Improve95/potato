package ru.improve.potato.core.validators.user;

import org.springframework.stereotype.Component;
import ru.improve.potato.api.dto.user.UserPostRequest;

@Component
public class UserValidatorImp implements UserValidator {

    @Override
    public boolean validateUserCreateRequest(UserPostRequest userPostRequest) {
        if (userPostRequest.getFirstName().length() < 0 || userPostRequest.getFirstName().length() > 50 ||
            userPostRequest.getSecondName().length() < 0 || userPostRequest.getSecondName().length() > 50 ||
                userPostRequest.getTelephoneNumber().length() != 11 ||
            userPostRequest.getPassword().length() < 0 || userPostRequest.getPassword().length() > 64) {
            /*другие проверки*/
            return false;
        }
        return true;
    }
}
