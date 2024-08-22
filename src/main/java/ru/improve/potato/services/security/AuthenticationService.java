package ru.improve.potato.services.security;

import ru.improve.potato.dto.session.LoginRequest;
import ru.improve.potato.dto.session.LoginResponse;
import ru.improve.potato.dto.user.UserPostRequest;
import ru.improve.potato.dto.user.UserPostResponse;

public interface AuthenticationService {
    UserPostResponse signUp(UserPostRequest userPostRequest);

    LoginResponse login(LoginRequest loginRequest);
}
