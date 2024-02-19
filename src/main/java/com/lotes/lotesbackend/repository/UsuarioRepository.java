package com.lotes.lotesbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lotes.lotesbackend.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	List<Usuario> getUsuariosByEmpresaId(Long empresaId);

}
