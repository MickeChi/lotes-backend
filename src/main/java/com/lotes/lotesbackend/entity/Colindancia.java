package com.lotes.lotesbackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "colindancias")
public class Colindancia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //TipoColindancia -> Enum = PARCELA, VIALIDAD, LOTE
    //LoteID
    //ParcelaId
    //Descripcion

}
