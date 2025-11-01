package com.Web.Plamilhas.Entity;

import java.time.OffsetDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "anexo")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class AnexoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne @JoinColumn(name = "compra_id")
    private CompraEntity compra;

    @ManyToOne @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    private String caminhoArquivo;
    private String nomeArquivo;
    private String tipoArquivo;
    private Long tamanho;
    private OffsetDateTime enviadoEm;
}
