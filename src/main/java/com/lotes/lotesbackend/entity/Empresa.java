package com.lotes.lotesbackend.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "empresas")
@Data
public class Empresa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "rfc")
	private String rfc;
	
	@Column(name = "razon_social")
	private String razonSocial;
	
	@Column(name = "nombre_comercial")
	private String nombreComercial;
	
	@Column(name = "direccion")
	private String direccion;
	
	@Column(name = "localidad")
	private String localidad;
	
	@Column(name = "estado")
	private String estado;
	
	@Column(name = "municipio")
	private String municipio;
	
	@Column(name = "telefono")
	private String telefono;
	
	@CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
	
}
