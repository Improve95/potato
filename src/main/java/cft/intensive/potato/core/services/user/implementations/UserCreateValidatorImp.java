package cft.intensive.potato.core.services.user.implementations;

import cft.intensive.potato.api.dto.transfer.user.UserPostRequest;
import cft.intensive.potato.core.services.user.UserCreateValidator;
import org.springframework.stereotype.Component;

@Component
public class UserCreateValidatorImp implements UserCreateValidator {

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
