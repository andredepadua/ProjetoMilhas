package com.Web.Plamilhas.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Web.Plamilhas.Entity.AnexoEntity;
import com.Web.Plamilhas.Repository.AnexoRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/anexos")
@RequiredArgsConstructor
public class AnexoController {
    private final AnexoRepository repo;

    @GetMapping
    public List<AnexoEntity> listar(){ return repo.findAll();}

    @PostMapping
    public AnexoEntity salvar(@RequestBody AnexoEntity anexo){ return repo.save(anexo);}
}


