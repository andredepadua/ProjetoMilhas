package com.Web.Plamilhas.Controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Web.Plamilhas.Entity.CompraEntity;
import com.Web.Plamilhas.Service.CompraService;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/compras")
public class CompraController {
    private final CompraService service;

    public CompraController(CompraService service){
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<CompraEntity> registrar (@RequestBody CompraEntity compra){
        return ResponseEntity.ok(service.registrarCompra(compra));
    }
    @GetMapping("/usuario/{usuarioId}")
    public List<CompraEntity> listarPorUsuario (@PathVariable UUID usuarioId){
        return service.listarPorUsuario(usuarioId);
    }
    

}
