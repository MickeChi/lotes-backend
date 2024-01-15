package com.lotes.lotesbackend.service;

import com.lotes.lotesbackend.dto.FraccionDTO;

import java.util.List;

public interface FraccionService extends BaseService<FraccionDTO, Long>{

    public List<FraccionDTO> getFraccionesByProyectoId(Long proyectoId);

}
