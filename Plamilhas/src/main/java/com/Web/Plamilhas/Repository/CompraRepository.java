package com.Web.Plamilhas.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Web.Plamilhas.Entity.CompraEntity;

public interface CompraRepository extends JpaRepository <CompraEntity,UUID> {

}
