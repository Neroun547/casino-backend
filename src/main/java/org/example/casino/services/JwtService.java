package org.example.casino.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {
    // Just test key
    SecretKey secretKey = Jwts.SIG.HS256.key().build();

    String generateJwtToken(Long userId, String username, String email) {
        return Jwts
                .builder()
                .signWith(secretKey)
                .subject(username)
                .expiration(new Date(System.currentTimeMillis() + 3600*1000))
                .claim("id", userId)
                .claim("username", username)
                .claim("email", email)
                .compact();
    }

    public boolean isTokenExpired(String token) {
        var payload = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return payload.getExpiration().before(new Date());
    }

    public Object extractUserName(String token) {
        var payload = Jwts.parser()
                .verifyWith(secretKey) // <----
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return payload.get("username");
    }
}
