package com.Web.Plamilhas.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Web.Plamilhas.Entity.ResgateEntity;

public interface ResgateRepository extends JpaRepository <ResgateEntity, UUID> {

}
