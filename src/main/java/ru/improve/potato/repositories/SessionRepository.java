package ru.improve.potato.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.improve.potato.models.Session;

import java.util.Optional;
import java.util.UUID;

public interface SessionRepository extends JpaRepository<Session, UUID> {

    Optional<Session> findByAccessToken(String accessToken);

    Optional<Session> findByRefreshToken(String refreshToken);
}
