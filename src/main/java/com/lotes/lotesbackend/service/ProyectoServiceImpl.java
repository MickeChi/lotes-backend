package com.lotes.lotesbackend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lotes.lotesbackend.dto.ProyectoDTO;
import com.lotes.lotesbackend.entity.Proyecto;
import com.lotes.lotesbackend.repository.ProyectoRepository;

@Service
public class ProyectoServiceImpl implements ProyectoService{
	
	@Autowired
	private ProyectoRepository proyectoRepository;
	
	@Autowired
    private ModelMapper modelMapper; 

	@Override
	public List<ProyectoDTO> findAll() {
		
		return this.proyectoRepository.findAll()
				.stream().map( p-> this.modelMapper.map(p, ProyectoDTO.class)).collect(Collectors.toList());
	}

	@Override
	public Optional<ProyectoDTO> findById(Long id) {
		Optional<ProyectoDTO> proyectoDtoOp = Optional.empty();
		Optional<Proyecto> proyOp = this.proyectoRepository.findById(id);
		if(proyOp.isPresent()) {
			proyectoDtoOp = Optional.of(this.modelMapper.map(proyOp.get(), ProyectoDTO.class)); 
		}
		return proyectoDtoOp;
	}

	@Override
	public ProyectoDTO save(ProyectoDTO proyectoDto) {
		
		Proyecto proy = this.proyectoRepository.save(this.modelMapper.map(proyectoDto, Proyecto.class));
		return this.modelMapper.map(proy, ProyectoDTO.class);
	}

	@Override
	public ProyectoDTO update(ProyectoDTO proyectoDto) {
		
		Optional<Proyecto> proyOp = this.proyectoRepository.findById(proyectoDto.getId());
		if(proyOp.isPresent()) {
			 Proyecto proy = this.proyectoRepository.save(this.modelMapper.map(proyectoDto, Proyecto.class));
			 proyectoDto = this.modelMapper.map(proy, ProyectoDTO.class);
		}		
		
		return proyectoDto;
		
	}

}
