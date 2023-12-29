package com.lotes.lotesbackend.dto;

import com.lotes.lotesbackend.constants.Orientacion;
import com.lotes.lotesbackend.constants.TipoLinea;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CotaRequestDTO {
	private Long id;
	
	private int orden;
	
	private TipoLinea tipoLinea;

    private Orientacion orientacion;
    
    private BigDecimal medida;
    
    private Long fraccionId;
    
    private List<Long> colindancias;


}
