package com.lotes.lotesbackend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.lotes.lotesbackend.constants.Estatus;
import com.lotes.lotesbackend.constants.TipoEntidad;
import com.lotes.lotesbackend.constants.TipoOperacion;
import com.lotes.lotesbackend.entity.Proyecto;
import com.lotes.lotesbackend.repository.OperacionRepository;
import com.lotes.lotesbackend.repository.ProyectoRepository;
import com.lotes.lotesbackend.utils.FraccionMaps;
import com.lotes.lotesbackend.utils.GenericMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lotes.lotesbackend.dto.FraccionDTO;
import com.lotes.lotesbackend.entity.Fraccion;
import com.lotes.lotesbackend.repository.FraccionRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FraccionServiceImpl implements FraccionService{
	
	@Autowired
	FraccionRepository fraccionRepository;

	@Autowired
	ProyectoRepository proyectoRepository;

	@Autowired
	private OperacionRepository operacionRepository;
	
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
	public List<FraccionDTO> findAll(Integer estatusId) {
		Estatus estatus = (estatusId == null) ? Estatus.ACTIVO : Estatus.of(estatusId);

		return this.fraccionRepository.getFraccionsByEstatus(estatus).stream()
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
	@Transactional
	public FraccionDTO save(FraccionDTO fraccionDto) {

		Fraccion frac = this.modelMapper.map(fraccionDto, Fraccion.class);
		Proyecto proy = this.modelMapper.map(fraccionDto.getProyecto(), Proyecto.class);
		frac.setProyecto(proy);
		Estatus estatus = frac.getEstatus() != null ? frac.getEstatus() : Estatus.ACTIVO;
		frac.setEstatus(estatus);

		frac = this.fraccionRepository.save(frac);
		this.operacionRepository.saveOperacion(TipoOperacion.CREATE, TipoEntidad.FRACCION, 1L, "fraccionId: " + frac.getId());

		return this.modelMapper.map(frac, FraccionDTO.class);
	}

	@Override
	@Transactional
	public FraccionDTO update(FraccionDTO fraccionDto) {
		Optional<Fraccion> fracOp = this.fraccionRepository.findById(fraccionDto.getId());
		if(fracOp.isPresent()) {
			Fraccion fraccionSave = this.modelMapper.map(fraccionDto, Fraccion.class);
			Proyecto proy = this.proyectoRepository.findById(fraccionDto.getProyectoId()).get();
			fraccionSave.setProyecto(proy);
			Estatus estatus = fraccionSave.getEstatus() != null ? fraccionSave.getEstatus() : Estatus.ACTIVO;
			fraccionSave.setEstatus(estatus);
			fraccionSave = this.fraccionRepository.save(fraccionSave);
			fraccionDto = this.modelMapper.map(fraccionSave, FraccionDTO.class);

			this.operacionRepository.saveOperacion(TipoOperacion.UPDATE, TipoEntidad.FRACCION, 1L, "fraccionId: " + fraccionDto.getId());

		}		
		
		return fraccionDto;
	}

	@Override
	public FraccionDTO delete(Long id) {
		Optional<Fraccion> fracOp = this.fraccionRepository.findById(id);
		Fraccion fracDelete = new Fraccion();
		if(fracOp.isPresent()) {
			fracDelete = fracOp.get();
			fracDelete.setEstatus(Estatus.DESACTIVADO);
			fracDelete = this.fraccionRepository.save(fracDelete);

			this.operacionRepository.saveOperacion(TipoOperacion.DELETE, TipoEntidad.FRACCION, 1L, "fraccionId: " + fracDelete.getId());

		}

		return this.modelMapper.map(fracDelete, FraccionDTO.class);
	}

	@Override
	public List<FraccionDTO> getFraccionesByProyectoId(Long proyectoId) {
		List<FraccionDTO> respuesta = new ArrayList<>();
		Optional<Proyecto> proyOp = this.proyectoRepository.findById(proyectoId);
		if(proyOp.isPresent()) {
			respuesta = this.fraccionRepository.getFraccionsByProyectoId(proyectoId).stream()
					.filter(p -> p.getEstatus().equals(Estatus.ACTIVO))
					.map(p-> {
						return this.modelMapper.map(p, FraccionDTO.class);
					})
					.collect(Collectors.toList());
		}

		return respuesta;
	}
}
