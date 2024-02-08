package com.lotes.lotesbackend.service;

import com.lotes.lotesbackend.dto.CotaDTO;
import com.lotes.lotesbackend.dto.FraccionDTO;

import java.util.List;

public interface CotaService extends BaseService<CotaDTO, Long>{

    public List<CotaDTO> getCotasByFraccionId(Long fraccionId);

}
