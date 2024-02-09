package com.lotes.lotesbackend.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


public class IndicadorOperacionKey implements Serializable {
    private Long tipoEntidadId;
    private String tipoEntidad;
    private Long tipoOperacionId;
    private String  tipoOperacion;
    private BigDecimal costoTotal;
    private Integer cantOperaciones;
    private Long usuarioId;
    private LocalDateTime fechaUltOperacion;

}
