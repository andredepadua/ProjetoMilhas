package com.Web.Plamilhas.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.Web.Plamilhas.Entity.UsuarioEntity;

public interface UsuarioService {
    UsuarioEntity criar( UsuarioEntity usuario);
    UsuarioEntity buscarPorId (UUID id);
    Optional<UsuarioEntity> buscarPorEmail(String email);
    List<UsuarioEntity> listarTodos();
    UsuarioEntity atualizar(UUID id, UsuarioEntity usuario);

}
