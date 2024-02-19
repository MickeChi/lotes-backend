package com.lotes.lotesbackend.repository;

import com.lotes.lotesbackend.constants.TipoEntidad;
import com.lotes.lotesbackend.constants.TipoIndicador;
import com.lotes.lotesbackend.constants.TipoOperacion;
import com.lotes.lotesbackend.entity.IndicadorOperacion;

import java.util.List;

public interface CustomOperacionRepository {
    public Long saveOperacion(TipoOperacion tipoOperacion, TipoEntidad tipoEntidad, Long usuarioId, String datos);

    public List<IndicadorOperacion> getIndicadoresOperaciones(Long usuarioId, TipoIndicador tipoIndicador);

}
