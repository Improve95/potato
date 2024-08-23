package ru.improve.potato.services.sessionAuth;

import ru.improve.potato.dto.session.CreateSessionRequest;
import ru.improve.potato.dto.session.CreateSessionResponse;

public interface SessionAuthService {

    CreateSessionResponse login(CreateSessionRequest createSessionRequest);
}
