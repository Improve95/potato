package ru.improve.potato.services.session;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.improve.potato.models.Session;
import ru.improve.potato.repositories.SessionRepository;
import ru.improve.potato.services.security.JwtService;
import ru.improve.potato.services.user.UserService;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class SessionServiceImp implements SessionService {

    private final UserService userService;

    private final SessionRepository sessionRepository;

    private final JwtService jwtService;

    @Transactional
    @Override
    public UUID save(Session session) {
        sessionRepository.save(session);
        return session.getId();
    }
}
