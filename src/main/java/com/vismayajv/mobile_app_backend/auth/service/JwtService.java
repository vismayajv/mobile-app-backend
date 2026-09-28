package com.vismayajv.mobile_app_backend.auth.service;

import java.util.Date;
import org.springframework.stereotype.Service;

import com.vismayajv.mobile_app_backend.auth.entity.user;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;


@Service 
public class JwtService {
    private final String secretKey =
            "my-super-secret-key-for-vehicle-app";

            public String generateToken(String email) {

    return Jwts.builder()
            .subject(email)
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
            .signWith(getSigningKey())
            .compact();
}
    private SecretKey getSigningKey() {

    return Keys.hmacShaKeyFor(
            secretKey.getBytes(StandardCharsets.UTF_8)
    );
}

public String extractEmail(String token) {

    return Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject();
}
public boolean isTokenValid(String token, user user) {
    try {
        String email = extractEmail(token);
        return email.equals(user.getEmail());
    } catch (Exception e) {
        return false;
    }
}
}
