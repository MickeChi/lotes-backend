package com.lotes.lotesbackend.dto;

import com.lotes.lotesbackend.entity.IndicadorOperacionKey;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class IndicadorOperacionDTO {

    private Long tipoEntidadId;

    private String tipoEntidad;

    private Long tipoOperacionId;

    private String  tipoOperacion;

    private BigDecimal costoTotal;

    private Integer cantOperaciones;

    private Long usuarioId;

    private LocalDateTime fechaUltOperacion;

}
