package com.Web.Plamilhas.Repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Web.Plamilhas.Entity.SaldoPontosEntity;
@Repository
public interface SaldoPontosRepository extends JpaRepository <SaldoPontosEntity,UUID> {
    Optional<SaldoPontosEntity> findByUsuarioIdAndProgramaId (UUID usuarioId, Integer programaId);
}
