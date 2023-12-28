package com.lotes.lotesbackend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class ProyectoDTO {
	
    private Long id;

    private String estado;

    private String municipio;

    private BigDecimal subtotal;

    private Integer totalFracciones;

    private String uso;

    private String clase;

    private String puntoPartida;

    //private List<FraccionDTO> fracciones;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    //Las colindancias del proyecto se crean como Fracciones con colindanciaProyecto = true

}
