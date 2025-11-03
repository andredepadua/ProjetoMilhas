package com.Web.Plamilhas.Security;

import java.security.Key;
import java.security.Signature;
import java.sql.Date;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.powermock.tests.utils.Keys;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    private static final String SECRET = "umasecretkeyseguraegrandeparajwtplaMilhas2025";
    private static final long EXPIRATION = 1000*60*60*24;

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());
    
    public String gerarToken (String email){
        Date agora = new Date(1);
        Date validade = new Date(agora.getTime() + EXPIRATION);
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(agora)
                .setExpiration(validade)
                .signWith(key,SignatureAlgorithm.HS256)
                .compact();
    }
    public String obterEmailDoToken(String token) {
        return Jwts.parserBuilder().verifyWith(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }
    public boolean validarToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e){
            return false;
        }
    }
}
