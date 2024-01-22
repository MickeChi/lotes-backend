package com.lotes.lotesbackend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.lotes.lotesbackend.constants.Orientacion;
import com.lotes.lotesbackend.constants.TipoLinea;

import lombok.Data;

@Data
public class CotaDTO {
	private Long id;
	
	private int orden;
	
	private TipoLinea tipoLinea;

    private Orientacion orientacion;
    
    private BigDecimal medida;
    
    private FraccionDTO fraccion;

    private Long fraccionId;

    private List<FraccionDTO> colindancias;

    private List<Long> colindanciasIds;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}
