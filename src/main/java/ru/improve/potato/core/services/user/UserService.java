package ru.improve.potato.core.services.user;

import ru.improve.potato.api.dto.user.UserGetResponse;
import ru.improve.potato.api.dto.user.UserPostResponse;
import ru.improve.potato.model.User;

public interface UserService {

    UserPostResponse createNewUser(User user);

    UserGetResponse getById(int id);

    void patchUserById(User user, int id);
}
