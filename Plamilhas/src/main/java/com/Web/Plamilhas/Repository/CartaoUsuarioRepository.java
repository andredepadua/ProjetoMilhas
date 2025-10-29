package com.Web.Plamilhas.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Web.Plamilhas.Entity.CartaoUsuarioEntity;

public interface CartaoUsuarioRepository extends JpaRepository <CartaoUsuarioEntity, UUID> {

}
