package com.lotes.lotesbackend.dto;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.lotes.lotesbackend.constants.Estatus;
import com.lotes.lotesbackend.constants.Orientacion;
import com.lotes.lotesbackend.constants.Uso;
import lombok.Data;

@Data
public class ProyectoDTO {
	
    private Long id;

    private String titulo;

    private String estado;

    private String municipio;

    private String localidad;

    private BigDecimal subtotal;

    private Integer totalFracciones;

    private Uso uso;

    private String clase;

    private Orientacion puntoPartida;

    private String nombreDocumento;

    private List<FraccionExternaDTO> fraccionesExternas = new ArrayList<>();

    private Estatus estatus;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    //Las colindancias del proyecto se crean como Fracciones con colindanciaProyecto = true

}
