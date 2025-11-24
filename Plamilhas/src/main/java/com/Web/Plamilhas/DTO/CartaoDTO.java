package com.Web.Plamilhas.DTO;

import java.util.UUID;
import lombok.Data;

@Data
public class CartaoDTO {
    private String numeroCartao;
    private String bandeira;
    private UUID usuarioId;
    private UUID programaId;
    private double pontosPorReal;
}


/*package com.Web.Plamilhas.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class CartaoDTO {
    private UUID usuarioId;
    private String numeroCartao;
    private String bandeira;

}*/
