package com.Web.Plamilhas.DTO;

import java.time.OffsetDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartaoUsuarioDTO {
    private Long id;
    private UUID usuarioId;
    private String numeroCartao;
    private String bandeira;
    private  OffsetDateTime criadoEm;

}
