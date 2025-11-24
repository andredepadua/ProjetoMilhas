package com.Web.Plamilhas.Service;

import com.Web.Plamilhas.DTO.CartaoDTO;
import com.Web.Plamilhas.Entity.CartaoUsuarioEntity;
import com.Web.Plamilhas.Repository.CartaoUsuarioRepository;
import com.Web.Plamilhas.Repository.ProgramaPontosRepository;
import com.Web.Plamilhas.Repository.UsuarioRepository;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class CartaoUsuarioService {

    private final CartaoUsuarioRepository repo;
    private final UsuarioRepository usuarioRepo;
    private final ProgramaPontosRepository programaRepo;

    public CartaoUsuarioService(
        CartaoUsuarioRepository repo,
        UsuarioRepository usuarioRepo,
        ProgramaPontosRepository programaRepo
    ) {
        this.repo = repo;
        this.usuarioRepo = usuarioRepo;
        this.programaRepo = programaRepo;
    }

    public CartaoUsuarioEntity criar(CartaoDTO dto) {

        CartaoUsuarioEntity c = CartaoUsuarioEntity.builder()
                .numeroCartao(dto.getNumeroCartao())
                .bandeira(dto.getBandeira())
                .usuario(usuarioRepo.findById(dto.getUsuarioId()).orElseThrow())
                .programa(programaRepo.findById(dto.getProgramaId()).orElseThrow())
                .pontosPorReal(dto.getPontosPorReal())
                .build();

        return repo.save(c);
    }

    public List<CartaoUsuarioEntity> listarPorUsuario(UUID id) {
        return repo.findByUsuarioId(id);
    }
}
