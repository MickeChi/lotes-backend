package com.lotes.lotesbackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity(name = "proyectos")
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "estado")
    private String estado;

    @Column(name = "municipio")
    private String municipio;

    @Column(name = "subtotal")
    private BigDecimal subtotal;

    @Column(name = "total_fracciones")
    private Integer totalFracciones;

    @Column(name = "uso")
    private String uso;

    @Column(name = "clase")
    private String clase;

    @Column(name = "punto_partida")
    private String puntoPartida;

    @OneToMany(mappedBy = "proyecto", fetch = FetchType.EAGER)
    private List<Fraccion> fracciones;

    @OneToMany(mappedBy = "proyecto", fetch = FetchType.EAGER)
    private List<Colindancia> colindancias;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
