package ru.improve.potato.services.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${signing.key}")
    private String jwtSigningKey;

    public String generateToken(int userId, UserDetails userDetails) {
        Date nowTime = new Date();
        Date expirationTime = new Date(nowTime.getTime() + 1000 * 60 * 60);

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("id", Integer.toString(userId))
                .setIssuedAt(nowTime)
                .setExpiration(expirationTime)
                .signWith(getJwtSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String verifyTokenAndGetSubject(String token) {
        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(getJwtSigningKey())
                .build()
                .parseClaimsJws(token);
//        claims.getBody().getExpiration().after(new Date())
        return claims.getBody().getSubject();
    }

    private Key getJwtSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
