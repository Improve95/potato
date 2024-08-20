package ru.improve.potato.core.services.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${secret.token}")
    private String secretKey;

    public String generateToken(UserDetails userDetails) {
        Instant nowTime = new Date().toInstant();
        Instant expirationTime = nowTime.plusSeconds(60 * 60);

        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withIssuedAt(nowTime)
                .withExpiresAt(expirationTime)
                .sign(Algorithm.HMAC256(secretKey));
    }

    public boolean tokenIsExpired(String token) {
        return getExpirationTime(token).isBefore(new Date().toInstant());
    }

    public Instant getExpirationTime(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getExpiresAt().toInstant();
    }

    public String verifyTokenAndGetSubject(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secretKey))
                .build();

        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        return decodedJWT.getSubject();
    }
}
