package com.lotes.lotesbackend.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProyectoTextoDTO {
    private long proyectoId;
    private List<FraccionTextoDTO> fraccionesTxt;
}
