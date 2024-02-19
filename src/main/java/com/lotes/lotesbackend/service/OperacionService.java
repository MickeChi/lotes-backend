package com.lotes.lotesbackend.service;

import com.lotes.lotesbackend.constants.TipoEntidad;
import com.lotes.lotesbackend.constants.TipoIndicador;
import com.lotes.lotesbackend.constants.TipoOperacion;
import com.lotes.lotesbackend.dto.IndicadorOperacionDTO;
import com.lotes.lotesbackend.dto.OperacionDTO;
import com.lotes.lotesbackend.entity.IndicadorOperacion;

import java.util.List;

public interface OperacionService {
    public Long saveOperacion(TipoOperacion tipoOperacion, TipoEntidad tipoEntidad, Long usuarioId, String datos);

    public List<IndicadorOperacionDTO> getIndicadoresOperaciones(Long usuarioId, TipoIndicador tipoIndicador);

    public List<OperacionDTO> findAll();
}
