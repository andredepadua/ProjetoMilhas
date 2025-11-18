package com.Web.Plamilhas.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Web.Plamilhas.Entity.CartaoUsuarioEntity;
@Repository
public interface CartaoUsuarioRepository extends JpaRepository <CartaoUsuarioEntity, UUID> {
    List<CartaoUsuarioEntity> findByUsuarioId (UUID usuarioId);
}
