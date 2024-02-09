package com.lotes.lotesbackend.dto;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OperacionDTO {

    private Long id;

    private Long usuarioId;

    private String datos;

    private Long tipoEntidadId;

    private Long tipoOperacionId;

    private Long tarifaOperacionId;

    private BigDecimal costo;

    private LocalDateTime createdAt;

}
