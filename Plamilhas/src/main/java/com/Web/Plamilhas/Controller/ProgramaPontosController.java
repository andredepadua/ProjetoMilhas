package com.Web.Plamilhas.Controller;

import com.Web.Plamilhas.DTO.ProgramaPontosDTO;
import com.Web.Plamilhas.Entity.ProgramaPontosEntity;
import com.Web.Plamilhas.Service.ProgramaPontosService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programas")
public class ProgramaPontosController {

    private final ProgramaPontosService service;

    public ProgramaPontosController(ProgramaPontosService service) {
        this.service = service;
    }

    @PostMapping
    public ProgramaPontosEntity criar(@RequestBody ProgramaPontosDTO dto) {
        return service.criar(dto);
    }

    @GetMapping
    public List<ProgramaPontosEntity> listar() {
        return service.listar();
    }
}
