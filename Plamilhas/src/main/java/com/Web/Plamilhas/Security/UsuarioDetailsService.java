package com.Web.Plamilhas.Security;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Web.Plamilhas.Entity.UsuarioEntity;
import com.Web.Plamilhas.Repository.UsuarioRepository;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository repository;

    public UsuarioDetailsService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsuarioEntity usuario = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));
                return new UsuarioPrincipal(usuario);

                
                /*return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getSenhaHash())
                .roles("USER")
                .disabled(!usuario.isAtivo())
                .build();*/
    }
}
