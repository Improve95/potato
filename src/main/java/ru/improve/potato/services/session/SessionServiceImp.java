package ru.improve.potato.services.session;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.improve.potato.error.exceptions.NotFoundException;
import ru.improve.potato.models.Session;
import ru.improve.potato.repositories.SessionRepository;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class SessionServiceImp implements SessionService {

    private final SessionRepository sessionRepository;

    @Override
    public Session getSessionByAccessToken(UUID id) {
         return sessionRepository.findById(id)
                 .orElseThrow(() -> new NotFoundException("session not found", List.of("id")));
    }

    @Override
    public Session getSessionByAccessToken(String accessToken) {
        return sessionRepository.findByAccessToken(accessToken).orElse(null);
    }

    @Override
    public Session getSessionByRefreshToken(String accessToken) {
        return sessionRepository.findByAccessToken(accessToken).orElse(null);
    }

    @Transactional
    @Override
    public UUID save(Session session) {
        sessionRepository.save(session);
        return session.getId();
    }
}
