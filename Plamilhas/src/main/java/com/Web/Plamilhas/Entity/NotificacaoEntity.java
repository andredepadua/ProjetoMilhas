package com.Web.Plamilhas.Entity;

import java.time.OffsetDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
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
@Table(name = "notificacao")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class NotificacaoEntity {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private UUID id;

@ManyToOne @JoinColumn(name = "usuario_id")
private UsuarioEntity usuario;

private String tipo;

@Column(columnDefinition = "jsonb")
private String conteudo;

private OffsetDateTime agendadaEm;
private OffsetDateTime enviadaEM;
private String status;
}
