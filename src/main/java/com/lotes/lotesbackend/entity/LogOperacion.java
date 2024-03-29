package com.lotes.lotesbackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity(name = "log_operaciones")
public class LogOperacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "datos")
    private String datos;

    @Column(name = "tipo_entidad_id")
    private Long tipoEntidadId;

    @Column(name = "tipo_operacion_id")
    private Long tipoOperacionId;

    @Column(name = "tarifa_operacion_id")
    private Long tarifaOperacionId;

    @Column(name = "costo")
    private BigDecimal costo;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;


}
