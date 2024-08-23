package ru.improve.potato.services.session;

import ru.improve.potato.models.Session;

import java.util.UUID;

public interface SessionService {

    Session getSessionByAccessToken(UUID id);

    Session getSessionByAccessToken(String accessToken);

    Session getSessionByRefreshToken(String accessToken);

    UUID save(Session session);
}
