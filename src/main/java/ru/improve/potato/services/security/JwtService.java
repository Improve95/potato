package ru.improve.potato.services.security;

import org.springframework.security.core.Authentication;
import ru.improve.potato.models.Session;
import ru.improve.potato.security.TokenRefresh;

import java.util.Date;
import java.util.UUID;

public interface JwtService {

    String generateAccessToken(UUID userId, String email);

    String generateRefreshToken(UUID userId, String email);

    TokenRefresh refreshUserToken(String refreshToken, Session session);

    boolean verifyToken(String token);

    String extractEmail(String token);

    Date extractExpirationTime(String token);

    Authentication getAuthentication(String accessToken);
}
