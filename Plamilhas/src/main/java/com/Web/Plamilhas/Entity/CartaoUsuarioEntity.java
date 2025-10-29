package com.Web.Plamilhas.Entity;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.stringtemplate.v4.compiler.CodeGenerator.primary_return;

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
@Table(name = "cartao_usuario")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class CartaoUsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    
    @ManyToOne @JoinColumn(name =  "usuario_id")
    private UsuarioEntity usuario;

    @ManyToOne @JoinColumn(name = "bandeira_id")
    private Bandeira bandeira;

    @ManyToOne @JoinColumn(name = "program_id")
    private ProgramaEntity programa;

    private String apelido;
    private String ultimos4;
    private String binPrefixo;
    private Boolean ativo;
    private OffsetDateTime criadoEm;

}
