package com.Web.Plamilhas.DTO.SaldoPontos;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaldoPontosDTO {
    private Long id;
    private UUID usuarioId;
    private Long programaId;
    private Double saldo;

}
