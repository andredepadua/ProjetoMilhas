package com.Web.Plamilhas.Controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import com.Web.Plamilhas.Entity.UsuarioEntity;
import com.Web.Plamilhas.Repository.UsuarioRepository;
import com.Web.Plamilhas.Security.JwtTokenProvider;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioRepository repo;
    private final JwtTokenProvider jwt;
    private final PasswordEncoder encoder;
    //private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthController(UsuarioRepository repo, JwtTokenProvider jwt, PasswordEncoder encoder) {
        this.repo = repo;
        this.jwt = jwt;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioEntity> register(@RequestBody UsuarioEntity usuario) {
        usuario.setSenhaHash(encoder.encode(usuario.getSenhaHash()));
        return ResponseEntity.ok(repo.save(usuario));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String senha = body.get("senha");

        var usuario = repo.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        if (!encoder.matches(senha, usuario.getSenhaHash())) {
            throw new RuntimeException("Senha incorreta");
        }

        String token = jwt.gerarToken(email);
        return ResponseEntity.ok(Map.of("token", token));
    }
}
