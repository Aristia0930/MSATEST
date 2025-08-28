package org.example.serviceauth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {
    private final SecretKey key;
    private final long accessExpMinutes;

    public JwtService(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.access-exp-minutes}") long accessExpMinutes
    ) {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(encodeIfNotBase64(secret)));
        this.accessExpMinutes = accessExpMinutes;
    }

    private static String encodeIfNotBase64(String s) {
        try { Decoders.BASE64.decode(s); return s; }
        catch (Exception e) { return Encoders.BASE64.encode(s.getBytes()); }

    }

    public String generateToken(String username){
        Instant now = Instant.now();

        return Jwts.builder()
                .claim("roles", "ROLE_USER")
                .setSubject(username)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(accessExpMinutes * 60)))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

}
