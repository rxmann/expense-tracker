package com.codex.jwt;

import io.jsonwebtoken.Jwts;
import jakarta.xml.bind.DatatypeConverter;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;

public class JwtServiceTest {


    @Test
    public void generateSecretKey () {
        SecretKey key = Jwts.SIG.HS256.key().build();
        String secretKey = DatatypeConverter.printHexBinary(key.getEncoded());
        System.out.println(secretKey);
    }


}
