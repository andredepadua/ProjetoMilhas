package com.Web.Plamilhas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.Web.Plamilhas.Security.JwtTokenProvider;

public class JwtTokenProviderTest {

    JwtTokenProvider provider = new JwtTokenProvider();

    @Test
    void deveGerarToken(){
        String token = provider.gerarToken("teste@teste.com");
        assertNotNull(token);
    }

    @Test
    void deveValidarToken(){
        String token = provider.gerarToken("teste@teste.com");
        assertTrue(provider.validarToken(token));
    }
    
    @Test
    void deveExtrairEmailDoTpken(){
        String token = provider.gerarToken("teste@teste.com");
        String email = provider.obterEmailDoToken(token);
        assertEquals("teste@teste.com", email);
    }
}