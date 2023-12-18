package com.lotes.lotesbackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity(name = "fracciones")
public class Fraccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fraccion")
    private Integer fraccion;

    @Column(name = "numero_catastral")
    private Long numeroCatastral;

    @Column(name = "finca", length = 300)
    private String finca;

    @Column(name = "tablaje")
    private Integer tablaje;

    @Column(name = "colonia", length = 400)
    private String colonia;

    @Column(name = "folio_electronico")
    private Long folioElectronico;

    @Column(name = "superficie_terreno")
    private BigDecimal superficieTerreno;

    @Column(name = "superficie_construccion")
    private BigDecimal superficieConstruccion;

    @Column(name = "valor_catastral")
    private BigDecimal valorCatastral;

    @Column(name = "uso")
    private String uso;

    @Column(name = "clase")
    private String clase;

    @Column(name = "descripcion", length = 200)
    private String descripcion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "proyecto_id", referencedColumnName = "id", nullable = false)
    private Proyecto proyecto;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}