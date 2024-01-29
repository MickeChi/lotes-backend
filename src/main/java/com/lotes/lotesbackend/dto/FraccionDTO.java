package com.lotes.lotesbackend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.lotes.lotesbackend.constants.TipoFraccion;
import com.lotes.lotesbackend.constants.Uso;
import lombok.Data;

@Data
public class FraccionDTO {

	private Long id;

    private Integer lote;

    private Long numeroCatastral;

    private String finca;

    private Integer tablaje;

    private String colonia;

    private Long folioElectronico;

    private BigDecimal superficieTerreno;

    private BigDecimal superficieConstruccion;

    private BigDecimal valorCatastral;

    private Uso uso;

    private String clase;

    private ProyectoDTO proyecto;

    private Long proyectoId;

    private List<CotaDTO> cotas;

    private TipoFraccion tipoFraccion;

    boolean colindanciaProyecto;

    private Integer numeroParcela;

    private String descripcion;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
	
	
	
	
}
