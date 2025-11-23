package com.Web.Plamilhas;

import com.Web.Plamilhas.Controller.AuthController;
import com.Web.Plamilhas.DTO.UsuarioLoginDTO;
import com.Web.Plamilhas.DTO.UsuarioRegisterDTO;
import com.Web.Plamilhas.Entity.UsuarioEntity;
import com.Web.Plamilhas.Repository.UsuarioRepository;
import com.Web.Plamilhas.Security.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class AuthControllerTest {

    private UsuarioRepository repo;
    private JwtTokenProvider jwt;
    private PasswordEncoder encoder;
    private AuthController controller;

   @BeforeEach
void setup() {
    repo = mock(UsuarioRepository.class);

    jwt = mock(JwtTokenProvider.class);
    when(jwt.gerarToken(anyString())).thenReturn("FAKE_TOKEN");

    encoder = mock(PasswordEncoder.class);
    when(encoder.encode(anyString())).thenReturn("HASH");
    when(encoder.matches(anyString(), anyString())).thenReturn(true);

    controller = new AuthController(repo, jwt, encoder);
}



    @Test
    void deveRegistrarUsuario() {
        UsuarioRegisterDTO dto = new UsuarioRegisterDTO();
        dto.setNomeCompleto("Andre");
        dto.setEmail("andre@test.com");
        dto.setSenha("123456");

        when(repo.findByEmail(dto.getEmail())).thenReturn(Optional.empty());
        when(encoder.encode(dto.getSenha())).thenReturn("HASH");
        when(repo.save(Mockito.any())).thenReturn(new UsuarioEntity());

        var result = controller.register(dto);

        assertEquals(200, result.getStatusCode().value());
    }

    @Test
void deveLogarUsuario() {
    UsuarioEntity u = new UsuarioEntity();
    u.setId(UUID.randomUUID()); // ← ESSENCIAL
    u.setEmail("andre@test.com");
    u.setSenhaHash("HASH");

    UsuarioLoginDTO dto = new UsuarioLoginDTO();
    dto.setEmail("andre@test.com");
    dto.setSenha("123456");

    when(repo.findByEmail(dto.getEmail())).thenReturn(Optional.of(u));
    when(encoder.matches(anyString(), anyString())).thenReturn(true);
    when(jwt.gerarToken(anyString())).thenReturn("FAKE_TOKEN");

    var result = controller.login(dto);

    assertEquals(200, result.getStatusCode().value());
}

}
