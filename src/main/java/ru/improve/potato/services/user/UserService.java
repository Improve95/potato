package ru.improve.potato.services.user;

import ru.improve.potato.dto.user.UserPostResponse;
import ru.improve.potato.dto.user.UserPatchRequest;
import ru.improve.potato.models.user.User;

public interface UserService {

    UserPostResponse save(User user);

    User getById(int id);

    User getByEmail(String email);

    void patchById(UserPatchRequest userPatchRequest, int id);
}
