package com.lotes.lotesbackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@Entity
@IdClass(IndicadorOperacionKey.class)
@SqlResultSetMapping(name = "IndicadorMapping", classes = {
        @ConstructorResult(targetClass = IndicadorOperacion.class, columns = {
                @ColumnResult(name = "tipo_entidad_id", type = Long.class),
                @ColumnResult(name = "tipo_entidad", type = String.class),
                @ColumnResult(name = "tipo_operacion_id", type = Long.class),
                @ColumnResult(name = "tipo_operacion", type = String.class),
                @ColumnResult(name = "costo_total", type = BigDecimal.class),
                @ColumnResult(name = "cant_operaciones", type = Integer.class),
                @ColumnResult(name = "usuario_id", type = Long.class),
                @ColumnResult(name = "fecha_ult_operacion", type = LocalDateTime.class)
        })
})
public class IndicadorOperacion{

    public IndicadorOperacion(Long tipoEntidadId, String tipoEntidad, Long tipoOperacionId, String tipoOperacion,
                              BigDecimal costoTotal, Integer cantOperaciones, Long usuarioId, LocalDateTime fechaUltOperacion) {
        this.tipoEntidadId = tipoEntidadId;
        this.tipoEntidad = tipoEntidad;
        this.tipoOperacionId = tipoOperacionId;
        this.tipoOperacion = tipoOperacion;
        this.costoTotal = costoTotal;
        this.cantOperaciones = cantOperaciones;
        this.usuarioId = usuarioId;
        this.fechaUltOperacion = fechaUltOperacion;
    }

    public IndicadorOperacion() {

    }

    @Id
    @Column(name = "tipo_entidad_id")
    private Long tipoEntidadId;

    @Id
    @Column(name = "tipo_entidad")
    private String tipoEntidad;

    @Id
    @Column(name = "tipo_operacion_id")
    private Long tipoOperacionId;

    @Id
    @Column(name = "tipo_operacion")
    private String  tipoOperacion;

    @Id
    @Column(name = "costo_total")
    private BigDecimal costoTotal;

    @Id
    @Column(name = "cant_operaciones")
    private Integer cantOperaciones;

    @Id
    @Column(name = "usuario_id")
    private Long usuarioId;

    @Id
    @Column(name = "fecha_ult_operacion")
    private LocalDateTime fechaUltOperacion;


}
