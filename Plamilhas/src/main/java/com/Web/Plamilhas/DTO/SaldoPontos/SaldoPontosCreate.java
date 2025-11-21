package com.Web.Plamilhas.DTO.SaldoPontos;

import java.util.UUID;

import lombok.Data;

@Data
public class SaldoPontosCreate {
    private UUID usuarioId;
    private Long programaId;

}
