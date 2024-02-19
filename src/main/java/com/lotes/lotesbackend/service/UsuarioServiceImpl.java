package com.lotes.lotesbackend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lotes.lotesbackend.dto.UsuarioDTO;
import com.lotes.lotesbackend.entity.Usuario;
import com.lotes.lotesbackend.repository.UsuarioRepository;
import com.lotes.lotesbackend.utils.GenericMapper;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	private final ModelMapper modelMapper;

	public UsuarioServiceImpl() {
		this.modelMapper = GenericMapper.getMapper();
		//this.modelMapper.addMappings(FraccionMaps.fraccionExternaDtoMap);

	}

	@Override
	public List<UsuarioDTO> findAll() {
		// TODO Auto-generated method stub
		return this.usuarioRepository.findAll().stream()
				.map(p -> this.modelMapper.map(p, UsuarioDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public Optional<UsuarioDTO> findById(Long id) {
		Optional<UsuarioDTO> usuarioDtoOp = Optional.empty();
		Optional<Usuario> userOp = this.usuarioRepository.findById(id);
		if(userOp.isPresent()) {
			UsuarioDTO userDto = this.modelMapper.map(userOp.get(), UsuarioDTO.class);
			usuarioDtoOp = Optional.of(userDto);

		}
		return usuarioDtoOp;
	}

	@Override
	public UsuarioDTO save(UsuarioDTO userDTO) {
		// TODO Auto-generated method stub
		Usuario usuario = this.usuarioRepository.save(this.modelMapper.map(userDTO, Usuario.class));
		return this.modelMapper.map(usuario, UsuarioDTO.class);
	}

	@Override
	public UsuarioDTO update(UsuarioDTO userDTO) {
		Optional<Usuario> userOp = this.usuarioRepository.findById(userDTO.getId());
		if(userOp.isPresent()) {
			Usuario usuario = this.usuarioRepository.save(this.modelMapper.map(userDTO, Usuario.class));
			userDTO = this.modelMapper.map(usuario, UsuarioDTO.class);
		}
		
		return userDTO;
		
	}

	@Override
	public List<UsuarioDTO> getUsuarioByEmpresaId(Long empresaId) {
		List<UsuarioDTO> respuesta = new ArrayList<>();
		respuesta = this.usuarioRepository.getUsuariosByEmpresaId(empresaId).stream()
				.map(p -> this.modelMapper.map(p, UsuarioDTO.class))
				.collect(Collectors.toList());
		
		return respuesta;
	}

}
