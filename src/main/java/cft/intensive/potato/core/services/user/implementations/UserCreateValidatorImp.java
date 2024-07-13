package cft.intensive.potato.core.services.user.implementations;

import cft.intensive.potato.api.dto.user.UserCreateRequest;
import cft.intensive.potato.core.services.user.UserCreateValidator;
import org.springframework.stereotype.Component;

@Component
public class UserCreateValidatorImp implements UserCreateValidator {

    @Override
    public boolean validateUserCreateRequest(UserCreateRequest userCreateRequest) {
        if (userCreateRequest.getFirstName().length() < 0 || userCreateRequest.getFirstName().length() > 50 ||
            userCreateRequest.getSecondName().length() < 0 || userCreateRequest.getSecondName().length() > 50 ||
                userCreateRequest.getTelephoneNumber().length() != 11 ||
            userCreateRequest.getPassword().length() < 0 || userCreateRequest.getPassword().length() > 64) {
            /*другие проверки*/
            return false;
        }
        return true;
    }
}
