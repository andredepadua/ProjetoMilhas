package com.Web.Plamilhas.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "conquista")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ConquistaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String codigo;
    private String titulo;
    private String descricao;

    @Column(columnDefinition = "jsonb")
    private String criterio;

    private Integer recompensaPontos;

}
