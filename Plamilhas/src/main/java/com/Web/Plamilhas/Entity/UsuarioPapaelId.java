package com.Web.Plamilhas.Entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data @NoArgsConstructor @AllArgsConstructor
public class UsuarioPapaelId implements Serializable{

    private UUID usuarioId;
    private Integer papelId;

    @Override
    public boolean equals(Object o){
        if (this == o)return true;
        if (o == null || getClass()!= o.getClass()) return false;
        UsuarioPapaelId that = (UsuarioPapaelId) o;
        return Objects.equals(usuarioId, that.usuarioId) && 
                Objects.equals(papelId, that.papelId);
    }

    @Override
    public int hashCode(){
        return Objects.hash(usuarioId,papelId);
    }

}
