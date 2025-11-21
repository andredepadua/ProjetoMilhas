package com.Web.Plamilhas.DTO.Compras;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Data;

@Data
public class CompraCreateDTO {
    private Double valor;
    private String moeda;
    private LocalDate dataCompra;
    private UUID usuarioId; 
    private Long programaId;

}
