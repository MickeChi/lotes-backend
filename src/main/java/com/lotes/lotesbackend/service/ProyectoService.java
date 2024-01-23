package com.lotes.lotesbackend.service;

import com.lotes.lotesbackend.dto.CotaProyectoDTO;
import com.lotes.lotesbackend.dto.ProyectoDTO;

public interface ProyectoService extends BaseService<ProyectoDTO, Long>{
	
	public CotaProyectoDTO saveCotaProyecto(CotaProyectoDTO cotaProyectoDto);

}
