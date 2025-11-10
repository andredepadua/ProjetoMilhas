package com.Web.Plamilhas.Service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.Web.Plamilhas.Entity.UsuarioEntity;
import com.Web.Plamilhas.Exception.ResourceNotFoundException;
import com.Web.Plamilhas.Repository.UsuarioRepository;
import com.Web.Plamilhas.Service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioRepository repository;

    public UsuarioServiceImpl(UsuarioRepository repository){
        this.repository = repository;
    }
    @Override
    public UsuarioEntity criar (UsuarioEntity usuario){
        return repository.save(usuario);
    }
    @Override
    public UsuarioEntity buscarPorId(UUID id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado"));
    }
    @Override
    public Optional<UsuarioEntity> buscarPorEmail(String email){
        return repository.findByEmail(email);
    }

@Override
public List< UsuarioEntity> listarTodos(){
    return repository.findAll();
}

@Override
public UsuarioEntity atualizar(UUID id, UsuarioEntity usuario){
    UsuarioEntity existente = buscarPorId(id);
    existente.setNomeCompleto(usuario.getNomeCompleto());
    existente.setEmail(usuario.getEmail());
    return repository.save(existente);
}

}
