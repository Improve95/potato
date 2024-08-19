package cft.intensive.potato.core.services.user;

import cft.intensive.potato.api.dto.transfer.user.UserPostRequest;
import cft.intensive.potato.api.dto.transfer.user.UserPostResponse;
import cft.intensive.potato.api.dto.transfer.user.UserGetResponse;
import cft.intensive.potato.api.dto.transfer.user.UserPatchRequest;

public interface UserService {

    UserPostResponse createUser(UserPostRequest userPostRequest);

    UserGetResponse getById(int id);

    void patchUserById(UserPatchRequest userPatchRequest, int id);
}
