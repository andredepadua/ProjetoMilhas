package com.Web.Plamilhas.Repository;

import com.Web.Plamilhas.Entity.ProgramaPontosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ProgramaPontosRepository extends JpaRepository<ProgramaPontosEntity, UUID> {}
