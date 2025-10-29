package com.Web.Plamilhas.Service;

import java.util.List;
import java.util.UUID;

import com.Web.Plamilhas.Entity.CompraEntity;

public interface CompraService {
    CompraEntity registrarCompra(CompraEntity compra);
    List<CompraEntity> listarPorUsuario(UUID usuarioId);

}
