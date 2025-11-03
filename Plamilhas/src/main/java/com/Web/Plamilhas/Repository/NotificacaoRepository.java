package com.Web.Plamilhas.Repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.Web.Plamilhas.Entity.NotificacaoEntity;

public interface NotificacaoRepository extends JpaRepository <NotificacaoEntity, UUID> {

}
