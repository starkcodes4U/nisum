package com.example.securelibrary.util;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Base64;

public class GenerateJwtSecretKey {
    public static void main(String[] args) {
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String secretString = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println("Generated JWT Secret Key:");
        System.out.println(secretString);
    }
}