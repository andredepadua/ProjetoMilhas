package com.Web.Plamilhas.Entity;

import java.time.OffsetDateTime;

import org.hibernate.validator.constraints.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario_papel")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
@IdClass(UsuarioPapelIdEntity.class)
public class UsuarioPapelEntity {
    @Id
    private UUID usuarioId;

    @Id 
    private Integer papelId;
    private OffsetDateTime atribuidoEm;

}
