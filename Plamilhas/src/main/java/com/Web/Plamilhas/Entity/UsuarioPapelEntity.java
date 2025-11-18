package com.Web.Plamilhas.Entity;

import java.time.OffsetDateTime;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario_papel")
@Data @Builder @NoArgsConstructor @AllArgsConstructor


public class UsuarioPapelEntity {
    @EmbeddedId
    private UsuarioPapaelId id;

    private OffsetDateTime atribuidoEm;

}
