package com.Web.Plamilhas.Service;

import com.Web.Plamilhas.DTO.ProgramaPontosDTO;
import com.Web.Plamilhas.Entity.ProgramaPontosEntity;
import com.Web.Plamilhas.Repository.ProgramaPontosRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProgramaPontosService {

    private final ProgramaPontosRepository repo;

    public ProgramaPontosService(ProgramaPontosRepository repo) {
        this.repo = repo;
    }

    public ProgramaPontosEntity criar(ProgramaPontosDTO dto) {
        ProgramaPontosEntity p = ProgramaPontosEntity.builder()
                .nome(dto.getNome())
                .multiplicador(dto.getMultiplicador())
                .build();

        return repo.save(p);
    }

    public List<ProgramaPontosEntity> listar() {
        return repo.findAll();
    }
}
