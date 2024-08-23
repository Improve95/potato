package ru.improve.potato.services.security;

import ru.improve.potato.dto.session.CreateSessionRequest;
import ru.improve.potato.dto.session.CreateSessionResponse;
import ru.improve.potato.dto.user.UserPostRequest;
import ru.improve.potato.dto.user.UserPostResponse;

public interface AuthenticationService {

    UserPostResponse signUp(UserPostRequest userPostRequest);

    CreateSessionResponse login(CreateSessionRequest createSessionRequest);
}
