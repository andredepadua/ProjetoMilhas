package com.Web.Plamilhas.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Web.Plamilhas.Entity.UsuarioEntity;
import com.Web.Plamilhas.Service.UsuarioService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service){
        this.service = service;
    }

    @GetMapping
    public List<UsuarioEntity> listar(){
        return service.listarTodos();
    }

    @PostMapping
    public ResponseEntity<UsuarioEntity> criar(@RequestBody UsuarioEntity usuario){
        return ResponseEntity.ok(service.criar(usuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEntity> buscar (@PathVariable UUID id){
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioEntity> atualizar(@PathVariable UUID id, @RequestBody UsuarioEntity usuario){
        return ResponseEntity.ok(service.atualizar(id, usuario));
    }
    
}
