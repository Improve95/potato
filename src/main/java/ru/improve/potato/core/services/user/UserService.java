package ru.improve.potato.core.services.user;

import ru.improve.potato.api.dto.user.UserPatchRequest;
import ru.improve.potato.api.dto.user.UserPostResponse;
import ru.improve.potato.model.User;

public interface UserService {

    UserPostResponse save(User user);

    User getById(int id);

    void patchById(UserPatchRequest userPatchRequest, int id);
}
