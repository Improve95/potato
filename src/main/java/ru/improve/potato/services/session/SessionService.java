package ru.improve.potato.services.session;

import ru.improve.potato.models.Session;

import java.util.UUID;

public interface SessionService {

    UUID save(Session session);
}
