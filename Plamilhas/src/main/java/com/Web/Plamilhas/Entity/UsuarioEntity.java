package com.Web.Plamilhas.Entity;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Data @Builder @NoArgsConstructor @AllArgsConstructor 
public class UsuarioEntity {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private UUID id;
@Column(nullable = false, length = 200)
private String nomeCompleto;

@Column(nullable = false, unique = true, length = 255)
private String email;

@Column(nullable = false)
private String senhaHash;

private boolean ativo = true;

private OffsetDateTime criadoEm;
private OffsetDateTime atualizadoEm;
private OffsetDateTime ultimoLogin;

@Column(columnDefinition = "jasonb")
private String perfil;

//Relacionamentos
@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
private List<CartaoUsuario> cartoes = new ArrayList<>();

@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
private List<Compra> compras = new ArrayList<>();

@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
private List<SaldoPontos> saldos = new ArrayList<>();
} 
