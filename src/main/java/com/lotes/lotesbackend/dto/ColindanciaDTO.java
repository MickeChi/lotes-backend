package com.lotes.lotesbackend.dto;

import com.lotes.lotesbackend.constants.TipoColindancia;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ColindanciaDTO {

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

    private TipoColindancia tipoColindancia;

    boolean colindanciaProyecto;

    private Integer numeroParcela;

    private String descripcion;

}
