package com.lotes.lotesbackend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.lotes.lotesbackend.entity.Proyecto;
import com.lotes.lotesbackend.utils.FraccionMaps;
import com.lotes.lotesbackend.utils.GenericMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lotes.lotesbackend.dto.FraccionDTO;
import com.lotes.lotesbackend.entity.Fraccion;
import com.lotes.lotesbackend.repository.FraccionRepository;

@Service
public class FraccionServiceImpl implements FraccionService{
	
	@Autowired
	FraccionRepository fraccionRepository;
	
	private final ModelMapper modelMapper;

	public FraccionServiceImpl() {
		this.modelMapper = GenericMapper.getMapper();
		this.modelMapper.addMappings(FraccionMaps.fraccionDtoMap);
	}

	@Override
	public List<FraccionDTO> findAll() {
		return this.fraccionRepository.findAll().stream()
		.map(p-> {
			return this.modelMapper.map(p, FraccionDTO.class);
		})
		.collect(Collectors.toList());
	}

	@Override
	public Optional<FraccionDTO> findById(Long id) {
		Optional<FraccionDTO> fracDtoOp = Optional.empty();
		Optional<Fraccion> fracOp = this.fraccionRepository.findById(id);
		if(fracOp.isPresent()) {
			fracDtoOp = Optional.of(this.modelMapper.map(fracOp.get(), FraccionDTO.class)); 
		}
		return fracDtoOp;
	}

	@Override
	public FraccionDTO save(FraccionDTO fraccionDto) {

		Fraccion frac = this.modelMapper.map(fraccionDto, Fraccion.class);
		Proyecto proy = this.modelMapper.map(fraccionDto.getProyecto(), Proyecto.class);
		frac.setProyecto(proy);

		frac = this.fraccionRepository.save(frac);
		return this.modelMapper.map(frac, FraccionDTO.class);
	}

	@Override
	public FraccionDTO update(FraccionDTO fraccionDto) {
		Optional<Fraccion> fracOp = this.fraccionRepository.findById(fraccionDto.getId());
		if(fracOp.isPresent()) {
			Fraccion proy = this.fraccionRepository.save(this.modelMapper.map(fraccionDto, Fraccion.class));
			 fraccionDto = this.modelMapper.map(proy, FraccionDTO.class);
		}		
		
		return fraccionDto;
	}

}
