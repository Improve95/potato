package cft.intensive.potato.core.services.user;

import cft.intensive.potato.api.dto.user.UserPostRequest;

public interface UserCreateValidator {

    boolean validateUserCreateRequest(UserPostRequest userPostRequest);
}
