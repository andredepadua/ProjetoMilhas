package com.Web.Plamilhas.DTO.TransferenciaPontos;

import java.time.OffsetDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransferenciaDTO {
    private Long id;
    private UUID remetendeId;
    private UUID destinatarioId;
    private Double pontos;
    private OffsetDateTime dataTransferencia;

}
