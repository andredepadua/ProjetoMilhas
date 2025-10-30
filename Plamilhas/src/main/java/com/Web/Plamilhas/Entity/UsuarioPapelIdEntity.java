package com.Web.Plamilhas.Entity;

import java.io.Serializable;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class UsuarioPapelIdEntity implments Serializable {

    private UUID usuarioId;
    private Integer papelId;
}

