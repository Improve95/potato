package cft.intensive.potato.core.services.user;

import cft.intensive.potato.api.dto.user.UserCreateRequest;

public interface UserCreateValidator {

    boolean validateUserCreateRequest(UserCreateRequest userCreateRequest);
}
