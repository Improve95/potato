package ru.improve.potato.services.sessionAuth;

import ru.improve.potato.dto.session.LoginRequest;
import ru.improve.potato.dto.session.LoginResponse;

public interface SessionAuthService {

    LoginResponse login(LoginRequest loginRequest);
}
