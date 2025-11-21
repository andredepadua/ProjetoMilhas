package com.Web.Plamilhas.DTO.Conversoes;

import java.time.OffsetDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConversaoDTO {
    private Long id;
    private Long programaId;
    private Long compraId;
    private Double pontosGerados;
    private OffsetDateTime criadoEm;


}
