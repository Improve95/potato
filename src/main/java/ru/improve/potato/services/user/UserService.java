package ru.improve.potato.services.user;

import ru.improve.potato.dto.user.UserPostResponse;
import ru.improve.potato.dto.user.UserPatchRequest;
import ru.improve.potato.models.User;

import java.util.UUID;

public interface UserService {

    UserPostResponse save(User user);

    User getById(UUID id);

    User getByEmail(String email);

    void patchById(UserPatchRequest userPatchRequest, UUID id);
}
