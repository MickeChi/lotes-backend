package com.lotes.lotesbackend.dto;

import java.math.BigDecimal;
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
public class PagoDTO {
	

	private Long id;
	
	private BigDecimal monto;
	
	private Integer creditos;
	
	private String referencia;
	
	private String metodo;
	
	private String canalPago;
	
	private EmpresaDTO empresa;
	
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
	
}
