package com.Web.Plamilhas.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class CartaoUsuarioCreateDTO {
    private UUID usuarioId;
    private String numeroCartao;
    private String bandeira;

}
