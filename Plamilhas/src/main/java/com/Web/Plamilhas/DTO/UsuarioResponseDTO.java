package com.Web.Plamilhas.DTO;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioResponseDTO {
    private UUID id;
    private String nomeCompleto;
    private String email;
    private boolean ativo;

}
