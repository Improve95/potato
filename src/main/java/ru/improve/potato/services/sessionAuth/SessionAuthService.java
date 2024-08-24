package ru.improve.potato.services.sessionAuth;

import jakarta.transaction.Transactional;
import ru.improve.potato.dto.session.CreateSessionRequest;
import ru.improve.potato.dto.session.SessionResponseDto;
import ru.improve.potato.dto.session.RefreshSessionRequest;
import ru.improve.potato.security.SessionUserDetails;

public interface SessionAuthService {

    SessionResponseDto login(CreateSessionRequest createSessionRequest);

    @Transactional
    void logout(SessionUserDetails sessionUserDetails);

    SessionResponseDto refreshSession(RefreshSessionRequest refreshSessionRequest);
}
