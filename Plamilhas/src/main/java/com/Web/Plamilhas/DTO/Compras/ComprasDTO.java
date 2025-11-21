package com.Web.Plamilhas.DTO.Compras;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ComprasDTO {   
    private Long id;
    private Double valor;
    private String moeda;
    private LocalDate dataCompra;
    private UUID usuarioId; 
    private Long programaId;

}
