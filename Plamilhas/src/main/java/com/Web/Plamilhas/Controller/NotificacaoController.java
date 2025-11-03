package com.Web.Plamilhas.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Web.Plamilhas.Entity.NotificacaoEntity;
import com.Web.Plamilhas.Repository.NotificacaoRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/notificacoes")
@RequiredArgsConstructor
public class NotificacaoController {
    private final NotificacaoRepository repo;
    @GetMapping 
    public List<NotificacaoEntity> listar(){ 
        return repo.findAll();
    }

}
