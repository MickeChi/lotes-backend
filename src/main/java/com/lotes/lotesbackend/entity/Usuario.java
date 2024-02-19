package com.lotes.lotesbackend.entity;

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

@Entity(name = "usuarios")
@Data
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "apellido_materno")
	private String apellidoMaterno;
	
	@Column(name = "apellido_paterno")
	private String apellidoPaterno;
	
	@Column(name = "curp")
	private String curp;
	
	@Column(name = "direccion")
	private String direccion;
	
	@Column(name = "telefono")
	private String telefono;
	
	@Column(name = "esAdmin")
	private boolean esAdmin;
	
	@Column(name = "status")
	private boolean status;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "avatar")
	private String avatar;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "empresa_id", referencedColumnName = "id", nullable = false)
	private Empresa empresa;
	
	@CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
