package com.Web.Plamilhas.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Web.Plamilhas.Entity.CompraEntity;
import java.util.List;


public interface CompraRepository extends JpaRepository <CompraEntity,UUID> {
    List<CompraEntity> findByUsuarioId (UUID usuarioId);
}
