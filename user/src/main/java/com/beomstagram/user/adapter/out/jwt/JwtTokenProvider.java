package com.beomstagram.user.adapter.out.jwt;

import com.beomstagram.user.application.port.out.AuthUserPort;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider implements AuthUserPort {

    private final Key key;
    private final long jwtTokenExpirationInMs;
    private final long refreshTokenExpirationInMs;

    public JwtTokenProvider(
            @Value("${token.secret}") String jwtSecret,
            @Value("${token.jwt.expiration}") String jwtTokenExpirationInMs,
            @Value("${token.refresh.expiration}") String refreshTokenExpirationInMs) {

        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.jwtTokenExpirationInMs = Long.parseLong(jwtTokenExpirationInMs);
        this.refreshTokenExpirationInMs = Long.parseLong(refreshTokenExpirationInMs);
    }

    @Override
    public String generateJwtToken(Long id, String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtTokenExpirationInMs);

        Claims claims = Jwts.claims();
        claims.put("userId", id);
        claims.put("username", username)
;
        String subject = String.valueOf(id);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    @Override
    public String generateRefreshToken(Long id) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + refreshTokenExpirationInMs);

        String subject = String.valueOf(id);
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }
}
