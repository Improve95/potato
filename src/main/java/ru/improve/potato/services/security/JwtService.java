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

import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${token.secret}")
    private String jwtSigningKey;
    
    @Value("${token.access.ttl}")
    private int accessTokenTimeOfLife;

    @Value("${token.refresh.ttl}")
    private int refreshTokenTimeOfLife;

    @PostConstruct
    private void init() {
        this.accessTokenTimeOfLife = accessTokenTimeOfLife * 1000;
        this.refreshTokenTimeOfLife = refreshTokenTimeOfLife * 1000;
    }

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

    public boolean verifyToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(getJwtSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return isExpired(claims.getBody().getExpiration());
        } catch (Exception ex) {
            return false;
        }
    }

    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getJwtSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    private boolean isExpired(Date expirationDate) {
        return expirationDate.after(new Date());
    }

    private Key getJwtSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
