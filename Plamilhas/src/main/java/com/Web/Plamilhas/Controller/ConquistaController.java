package com.Web.Plamilhas.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Web.Plamilhas.Entity.ConquistaEntity;
import com.Web.Plamilhas.Repository.ConquistaRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/conquistas")
@RequiredArgsConstructor
public class ConquistaController {
    private final ConquistaRepository repo;
    @GetMapping
    public List<ConquistaEntity> listar(){
        return repo.findAll();
    }

}
