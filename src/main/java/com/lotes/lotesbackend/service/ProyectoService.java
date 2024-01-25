package com.lotes.lotesbackend.service;

import com.lotes.lotesbackend.dto.FraccionExternaDTO;
import com.lotes.lotesbackend.dto.ProyectoDTO;

public interface ProyectoService extends BaseService<ProyectoDTO, Long>{
	
	public FraccionExternaDTO saveFraccionExterna(FraccionExternaDTO fraccionExternaDto);

}
