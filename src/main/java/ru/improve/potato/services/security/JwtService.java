package ru.improve.potato.services.security;

import java.util.UUID;

public interface JwtService {
    String generateAccessToken(UUID userId, String email);

    String generateRefreshToken(UUID userId, String email);

    boolean verifyToken(String token);

    String extractEmail(String token);
}
