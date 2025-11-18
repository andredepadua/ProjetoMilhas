package com.Web.Plamilhas.Service.impl;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.Web.Plamilhas.Entity.CompraEntity;
import com.Web.Plamilhas.Entity.ProgramaEntity;
import com.Web.Plamilhas.Exception.ResourceNotFoundException;
import com.Web.Plamilhas.Repository.CompraRepository;
import com.Web.Plamilhas.Repository.ProgramaRepository;
import com.Web.Plamilhas.Service.CompraService;
@Service
public class CompraServiceImpl implements CompraService{
    private final CompraRepository compraRepo;
    private final ProgramaRepository programaRepo;
    public CompraServiceImpl(CompraRepository compraRepo, ProgramaRepository programaRepo){
        this.compraRepo = compraRepo;
        this.programaRepo = programaRepo;
    }

    @Override
    public CompraEntity registrarCompra(CompraEntity compra){

        if(compra.getPrograma() == null || compra.getPrograma().getId() == null){
            throw new IllegalArgumentException("O ID do programa não pode ser nulo!");
        }

        Integer programaId = compra.getPrograma().getId();

        ProgramaEntity programa = programaRepo.findById(programaId).orElseThrow(() -> new ResourceNotFoundException("Programa com ID" + programaId + "não encontrado."));
        compra.setPrograma(programa);
        compra.setRegistradaEm(OffsetDateTime.now());
        return compraRepo.save(compra);
    }
    @Override
    public List<CompraEntity> listarPorUsuario(UUID usuarioId){
        return compraRepo.findByUsuarioId(usuarioId);
    }

}
