package com.lotes.lotesbackend.service;

import com.lotes.lotesbackend.dto.FraccionExternaDTO;
import com.lotes.lotesbackend.dto.ProyectoDTO;
import com.lotes.lotesbackend.dto.ProyectoTextoDTO;

import java.util.Map;

public interface ProyectoService extends BaseService<ProyectoDTO, Long>{
	
	public FraccionExternaDTO saveFraccionExterna(FraccionExternaDTO fraccionExternaDto);

	public ProyectoTextoDTO generateFraccionesText(Long proyectoId);

}
