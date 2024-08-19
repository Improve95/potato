package ru.improve.potato.core.services.user;

import ru.improve.potato.api.dto.user.UserPostRequest;
import ru.improve.potato.api.dto.user.UserPostResponse;
import ru.improve.potato.api.dto.user.UserGetResponse;
import ru.improve.potato.api.dto.user.UserPatchRequest;

public interface UserService {

    UserPostResponse createUser(UserPostRequest userPostRequest);

    UserGetResponse getById(int id);

    void patchUserById(UserPatchRequest userPatchRequest, int id);
}
