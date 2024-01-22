package com.lotes.lotesbackend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.lotes.lotesbackend.constants.Orientacion;
import com.lotes.lotesbackend.constants.Uso;
import lombok.Data;

@Data
public class ProyectoDTO {
	
    private Long id;

    private String titulo;

    private String estado;

    private String municipio;

    private BigDecimal subtotal;

    private Integer totalFracciones;

    private Uso uso;

    private String clase;

    private Orientacion puntoPartida;

    //private List<FraccionDTO> fracciones;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    //Las colindancias del proyecto se crean como Fracciones con colindanciaProyecto = true

}
