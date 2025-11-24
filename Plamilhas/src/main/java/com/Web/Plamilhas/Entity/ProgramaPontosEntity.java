package com.Web.Plamilhas.Entity;

import java.util.UUID;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "programa_pontos")
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class ProgramaPontosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String nome;

    // multiplicador ex: 1.2 (20% bônus)
    private double multiplicador = 1.0;
}

