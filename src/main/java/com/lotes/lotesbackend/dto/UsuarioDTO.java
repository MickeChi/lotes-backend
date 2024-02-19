package com.lotes.lotesbackend.dto;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class UsuarioDTO {
	
	private Long id;
	
	private String nombre;
	
	private String apellidoMaterno;
	
	private String apellidoPaterno;
	
	private String curp;
	
	private String direccion;
	
	private String telefono;
	
	private boolean esAdmin;
	
	private boolean status;
	
	private String email;
	
	private String password;
	
	private String avatar;
	
	private EmpresaDTO empresa;
	
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
