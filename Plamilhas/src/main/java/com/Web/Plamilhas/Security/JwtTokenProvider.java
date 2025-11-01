package com.Web.Plamilhas.Security;

import java.security.Key;
import java.sql.Date;

import org.powermock.tests.utils.Keys;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    private static final String SECRET = "umasecretkeyseguraegrandeparajwtplaMilhas2025";
    private static final long EXPIRATION = 1000*60*60*24;

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());
    
    public String gerarToken (String email){
        Date agora = new Date();
        Date validade = new Date(agora.getTime() + EXPIRATION);

        return Jwts


    }
}
