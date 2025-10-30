package br.com.plamilhas.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.util.*;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final String SECRET = "umasecretkeyseguraegrandeprajwtplaMilhas2025";
    private static final long EXPIRATION = 1000 * 60 * 60 * 24; // 24 horas

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String gerarToken(String email) {
        Date agora = new Date();
        Date validade = new Date(agora.getTime() + EXPIRATION);
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(agora)
                .setExpiration(validade)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String obterEmailDoToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validarToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
