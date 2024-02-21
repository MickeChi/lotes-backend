package com.lotes.lotesbackend.entity;

import com.lotes.lotesbackend.constants.Estatus;
import com.lotes.lotesbackend.constants.TipoFraccion;
import com.lotes.lotesbackend.constants.Uso;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity(name = "fracciones")
public class Fraccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lote")
    private Integer lote;

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

    private Uso uso;

    @Column(name = "clase")
    private String clase;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "proyecto_id", referencedColumnName = "id", nullable = false)
    private Proyecto proyecto;

    @OneToMany(mappedBy = "fraccion", fetch = FetchType.EAGER)
    private List<Cota> cotas;

    private TipoFraccion tipoFraccion;

    @Column(name = "colindanciaProyecto")
    boolean colindanciaProyecto;

    @Column(name = "numero_parcela")
    private Integer numeroParcela;

    @Column(name = "descripcion", length = 200)
    private String descripcion;

    @Column(name = "estatus", columnDefinition = "integer default 1")
    private Estatus estatus;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
