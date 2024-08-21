package ru.improve.potato.services.user;

import ru.improve.potato.dto.auth.SignUpResponse;
import ru.improve.potato.dto.user.UserPatchRequest;
import ru.improve.potato.models.user.User;

public interface UserService {

    SignUpResponse save(User user);

    User getById(int id);

    User getByPhone(String phone);

    void patchById(UserPatchRequest userPatchRequest, int id);
}
