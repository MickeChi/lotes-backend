package com.lotes.lotesbackend.dto;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class EmpresaDTO {
	
	private Long id;
	
	private String rfc;
	
	private String razonSocial;
	
	private String nombreComercial;
	
	private String direccion;
	
	private String localidad;
	
	private String estado;
	
	private String municipio;
	
	private String telefono;
	
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
	
}
