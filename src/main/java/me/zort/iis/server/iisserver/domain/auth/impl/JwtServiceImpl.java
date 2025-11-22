package me.zort.iis.server.iisserver.domain.auth.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import me.zort.iis.server.iisserver.config.AppConfig;
import me.zort.iis.server.iisserver.domain.auth.JwtService;
import me.zort.iis.server.iisserver.domain.auth.exception.TokenInvalidException;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {
    private static final long ACCESS_TOKEN_EXPIRATION = 1000L * 60 * 15; // 15 mins
    private static final long REFRESH_TOKEN_EXPIRATION = 1000L * 60 * 60 * 24 * 30; // 30 days

    private final Key key;

    public JwtServiceImpl(AppConfig appConfig) {
        this.key = Keys.hmacShaKeyFor(appConfig.getJwtSecret().getBytes());
    }

    @Override
    public String generateRefreshToken(long userId) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String generateAccessToken(long userId) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public long validateToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return Long.parseLong(claims.getSubject());
        } catch (JwtException | IllegalArgumentException e) {
            throw new TokenInvalidException();
        }
    }
}
