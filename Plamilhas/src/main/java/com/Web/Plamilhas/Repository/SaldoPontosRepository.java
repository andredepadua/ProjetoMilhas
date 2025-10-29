package com.Web.Plamilhas.Repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Web.Plamilhas.Entity.SaldoPontosEntity;

public interface SaldoPontosRepository extends JpaRepository <SaldoPontosEntity,UUID> {
    Optional<SaldoPontosEntity> findByUsuarioIdAndProgramaId (UUID usuarioId, Integer programaId);
}
