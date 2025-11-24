package com.Web.Plamilhas.Controller;

import com.Web.Plamilhas.DTO.CartaoDTO;
import com.Web.Plamilhas.Entity.CartaoUsuarioEntity;
import com.Web.Plamilhas.Service.CartaoUsuarioService;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cartoes")
public class CartaoUsuarioController {

    private final CartaoUsuarioService service;

    public CartaoUsuarioController(CartaoUsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public CartaoUsuarioEntity criar(@RequestBody CartaoDTO dto) {
        return service.criar(dto);
    }

    @GetMapping("/usuario/{id}")
    public List<CartaoUsuarioEntity> listarPorUsuario(@PathVariable UUID id) {
        return service.listarPorUsuario(id);
    }
}
