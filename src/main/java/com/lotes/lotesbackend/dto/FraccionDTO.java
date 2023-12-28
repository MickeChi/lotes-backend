package com.lotes.lotesbackend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.lotes.lotesbackend.constants.TipoColindancia;
import lombok.Data;

@Data
public class FraccionDTO {

	private Long id;

    private Integer fraccion;

    private Long numeroCatastral;

    private String finca;

    private Integer tablaje;

    private String colonia;

    private Long folioElectronico;

    private BigDecimal superficieTerreno;

    private BigDecimal superficieConstruccion;

    private BigDecimal valorCatastral;

    private String uso;

    private String clase;

    private ProyectoDTO proyecto;

    private List<CotaDTO> cotas;

    //Datos Colindancia
    private TipoColindancia tipoColindancia;

    boolean colindanciaProyecto;

    private Integer numeroParcela;

    private String descripcion;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
	
	
	
	
}
