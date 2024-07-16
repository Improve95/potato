package cft.intensive.potato.core.services.user;

import cft.intensive.potato.api.dto.user.UserPostRequest;
import cft.intensive.potato.api.dto.user.UserPostResponse;
import cft.intensive.potato.api.dto.user.UserGetResponse;
import cft.intensive.potato.api.dto.user.UserPatchRequest;

public interface UserService {

    UserPostResponse createUser(UserPostRequest userPostRequest);

    UserGetResponse getById(int id);

    void patchUserById(UserPatchRequest userPatchRequest, int id);
}
