package ru.improve.potato.core.validators.user;

import ru.improve.potato.api.dto.user.UserPostRequest;

public interface UserValidator {

    boolean validateUserCreateRequest(UserPostRequest userPostRequest);
}
