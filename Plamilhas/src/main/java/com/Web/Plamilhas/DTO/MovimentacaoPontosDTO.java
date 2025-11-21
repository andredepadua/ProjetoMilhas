package com.Web.Plamilhas.DTO;

import java.time.OffsetDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovimentacaoPontosDTO {
    private Long id;
    private Long saldoId;
    private String tipo;
    private Double quantidade;
    private OffsetDateTime data;

}
