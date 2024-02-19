package com.lotes.lotesbackend.service;

import java.util.List;

import com.lotes.lotesbackend.dto.UsuarioDTO;

public interface UsuarioService extends BaseService<UsuarioDTO, Long>{
	 public List<UsuarioDTO> getUsuarioByEmpresaId(Long empresaId);

}
