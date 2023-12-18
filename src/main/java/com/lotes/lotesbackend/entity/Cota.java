package com.lotes.lotesbackend.entity;

import com.lotes.lotesbackend.constants.Orientacion;
import com.lotes.lotesbackend.constants.TipoLinea;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity(name = "cotas")
public class Cota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "orden")
    private int orden;

    private TipoLinea tipoLinea;

    private Orientacion orientacion;

    @Column(name = "medida")
    private BigDecimal medida;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fraccion_id", referencedColumnName = "id", nullable = false)
    private Fraccion fraccion;

    @OneToMany(mappedBy = "cota", fetch = FetchType.EAGER)
    private List<Colindancia> colindancias;

}
