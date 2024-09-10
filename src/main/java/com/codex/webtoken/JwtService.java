package com.codex.webtoken;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Service
public class JwtService {

    private static final String SECRET_KEY = "32DF1A433A2EB38FBE9321972A2343D4BED73960AEDAC565E0E01012EB85A97C";
    private static final long JWT_VALIDITY = TimeUnit.MINUTES.toMillis(1);


    public String generateToken (UserDetails userDetails) {

        Map<String, String> claims = new HashMap<>();
        claims.put("iss", "com.codex.expense-tracker");

        return Jwts.builder()
                .claims(claims) // additional fields for payload
                .subject(userDetails.getUsername())
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusMillis(JWT_VALIDITY)))
                .signWith(generateKey()) // Signed Key
                .compact(); // To string (JSON)
    }

    private SecretKey generateKey () {
        byte[] decodedKey = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(decodedKey);
    }
}

