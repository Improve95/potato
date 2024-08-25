package ru.improve.potato.services.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.improve.potato.error.exceptions.security.ExpiredSessionException;
import ru.improve.potato.error.exceptions.security.IncorrectJwtTokenException;
import ru.improve.potato.models.Session;
import ru.improve.potato.models.User;
import ru.improve.potato.security.TokenRefresh;
import ru.improve.potato.services.session.SessionService;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtServiceImp implements JwtService {

    @Value("${token.secret}")
    private String jwtSigningKey;
    
    @Value("${token.access.ttl}")
    private int accessTokenTimeOfLife;

    @Value("${token.refresh.ttl}")
    private int refreshTokenTimeOfLife;

    private final SessionService sessionService;

    @PostConstruct
    private void init() {
        this.accessTokenTimeOfLife = accessTokenTimeOfLife * 1000;
        this.refreshTokenTimeOfLife = refreshTokenTimeOfLife * 1000;
    }

    @Override
    public String generateAccessToken(UUID userId, String email) {
        Date nowTime = new Date();
        Date expirationTime = new Date(nowTime.getTime() + accessTokenTimeOfLife);

        return Jwts.builder()
                .setSubject(email)
                .claim("id", userId)
                .setIssuedAt(nowTime)
                .setExpiration(expirationTime)
                .signWith(getJwtSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String generateRefreshToken(UUID userId, String email) {
        Date nowTime = new Date();
        Date expirationTime = new Date(nowTime.getTime() + refreshTokenTimeOfLife);

        return Jwts.builder()
                .claim("id", userId)
                .claim("email", email)
                .setIssuedAt(nowTime)
                .setExpiration(expirationTime)
                .signWith(getJwtSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public TokenRefresh refreshUserToken(String refreshToken, Session session) {
        User user = session.getUser();

        if (!verifyToken(refreshToken)) {
            session.setEnabled(false);
            sessionService.save(session);

            throw new IncorrectJwtTokenException("incorrect refreshToken", List.of("refreshToken"));
        }

        return TokenRefresh.builder()
                .accessToken(generateAccessToken(user.getId(), user.getEmail()))
                .refreshToken(generateRefreshToken(user.getId(), user.getEmail()))
                .build();
    }

    @Override
    public boolean verifyToken(String token) {
        Jws<Claims> claims;

        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(getJwtSigningKey())
                    .build()
                    .parseClaimsJws(token);
        } catch (Exception ex) {
            return false;
        }

        if (!isValidByTime(claims.getBody().getExpiration())) {
            throw new ExpiredSessionException("token is expired");
        }

        return true;
    }

    @Override
    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getJwtSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    @Override
    public Date extractExpirationTime(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getJwtSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody().getExpiration();
    }

    @Override
    public boolean isValidByTime(Date expirationDate) {
        return expirationDate.after(new Date());
    }

    private Key getJwtSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
