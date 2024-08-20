package ru.improve.potato.core.services.user;

import ru.improve.potato.api.dto.auth.SignUpResponse;
import ru.improve.potato.api.dto.user.UserPatchRequest;
import ru.improve.potato.model.user.User;

public interface UserService {

    SignUpResponse save(User user);

    User getById(int id);

    void patchById(UserPatchRequest userPatchRequest, int id);
}
