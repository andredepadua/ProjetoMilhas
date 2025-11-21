package com.Web.Plamilhas.DTO.TransferenciaPontos;

import java.util.UUID;

import lombok.Data;

@Data
public class TranferenciaCreateDTO {
    private UUID remetendeId;
    private UUID destinatarioId;
    private Double pontos;

}
