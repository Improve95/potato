package cft.intensive.potato.core.services.user;

import cft.intensive.potato.api.dto.user.UserCreateRequest;
import cft.intensive.potato.api.dto.user.UserCreateResponse;
import cft.intensive.potato.api.dto.user.UserGetResponse;
import cft.intensive.potato.api.dto.user.UserPatchRequest;

public interface UserService {

    UserCreateResponse createUser(UserCreateRequest userCreateRequest);

    UserGetResponse getById(int id);

    void patchUserById(UserPatchRequest userPatchRequest, int id);
}
