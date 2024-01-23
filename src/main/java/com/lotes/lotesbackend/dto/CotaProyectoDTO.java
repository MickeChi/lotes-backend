package com.lotes.lotesbackend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.lotes.lotesbackend.constants.Orientacion;
import com.lotes.lotesbackend.constants.TipoLinea;

import lombok.Data;

@Data
public class CotaProyectoDTO {

    private Long fraccionId;
    
    private Long proyectoId;
    
	private Long cotaId;
    
    private String descripcion;
	
	private int orden;
	
	private TipoLinea tipoLinea;

    private Orientacion orientacion;
    
    private BigDecimal medida;
    
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    
    public void setDatosCota(CotaDTO cotaDto) {
    	this.tipoLinea = cotaDto.getTipoLinea();
    	this.orientacion = cotaDto.getOrientacion();
    	this.medida = cotaDto.getMedida();
    	this.orden = cotaDto.getOrden();
    	this.cotaId = cotaDto.getId();
    	
    };


}
