package com.Web.Plamilhas.Entity;

import java.time.OffsetDateTime;

import java.util.UUID;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario_papel")
@Data @Builder @NoArgsConstructor @AllArgsConstructor @Embeddable


public class UsuarioPapelEntity {
    
    private UUID usuarioId;


    private Integer papelId;
    private OffsetDateTime atribuidoEm;

}
