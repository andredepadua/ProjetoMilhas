package com.Web.Plamilhas.Service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.Web.Plamilhas.Entity.CompraEntity;
import com.Web.Plamilhas.Repository.CompraRepository;
import com.Web.Plamilhas.Service.CompraService;
@Service
public class CompraServiceImpl implements CompraService{
    private final CompraRepository repo;
    public CompraServiceImpl(CompraRepository repo){
        this.repo = repo;
    }

    @Override
    public CompraEntity registrarCompra(CompraEntity compra){
        return repo.save(compra);
    }
    @Override
    public List<CompraEntity> listarPorUsuario(UUID usuarioId){
        return repo.findByUsuarioId(usuarioId);
    }

}
