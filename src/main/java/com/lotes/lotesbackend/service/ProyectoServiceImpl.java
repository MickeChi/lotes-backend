package com.lotes.lotesbackend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.lotes.lotesbackend.utils.FraccionMaps;
import com.lotes.lotesbackend.utils.GenericMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lotes.lotesbackend.dto.CotaProyectoDTO;
import com.lotes.lotesbackend.dto.ProyectoDTO;
import com.lotes.lotesbackend.entity.Cota;
import com.lotes.lotesbackend.entity.Fraccion;
import com.lotes.lotesbackend.entity.Proyecto;
import com.lotes.lotesbackend.repository.CotaRepository;
import com.lotes.lotesbackend.repository.FraccionRepository;
import com.lotes.lotesbackend.repository.ProyectoRepository;

@Service
public class ProyectoServiceImpl implements ProyectoService{
	
	@Autowired
	private ProyectoRepository proyectoRepository;
	
	@Autowired
	FraccionRepository fraccionRepository;
	
	@Autowired
	CotaRepository cotaRepository;

    private final ModelMapper modelMapper;

	public ProyectoServiceImpl() {
		this.modelMapper = GenericMapper.getMapper();
		this.modelMapper.addMappings(FraccionMaps.cotaProyDtoMap);

	}

	@Override
	public List<ProyectoDTO> findAll() {
		
		return this.proyectoRepository.findAll()
				.stream().map( p-> this.modelMapper.map(p, ProyectoDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public Optional<ProyectoDTO> findById(Long id) {
		Optional<ProyectoDTO> proyectoDtoOp = Optional.empty();
		Optional<Proyecto> proyOp = this.proyectoRepository.findById(id);
		if(proyOp.isPresent()) {
			List<CotaProyectoDTO> listCotasProy = this.fraccionRepository.getFraccionsByProyectoId(proyOp.get().getId())
					.stream().map(p-> this.modelMapper.map(p, CotaProyectoDTO.class)).collect(Collectors.toList());
			ProyectoDTO proyDto = this.modelMapper.map(proyOp.get(), ProyectoDTO.class);
			proyDto.setCotasProyecto(listCotasProy);
			proyectoDtoOp = Optional.of(proyDto); 
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

	@Override
	public CotaProyectoDTO saveCotaProyecto(CotaProyectoDTO cotaProyectoDto) {
		Optional<Proyecto> proyOp = this.proyectoRepository.findById(cotaProyectoDto.getProyectoId());
		if(proyOp.isPresent()) {
			Fraccion frac = new Fraccion();
			frac.setDescripcion(cotaProyectoDto.getDescripcion());
			frac.setProyecto(proyOp.get());
			
			frac = this.fraccionRepository.save(frac);
			
			Cota cotap = new Cota();
			cotap.setColindancias(new ArrayList<>());
			cotap.setFraccion(frac);
			cotap.setMedida(cotaProyectoDto.getMedida());
			cotap.setOrden(cotaProyectoDto.getOrden());
			cotap.setOrientacion(cotaProyectoDto.getOrientacion());
			cotap.setTipoLinea(cotaProyectoDto.getTipoLinea());
			cotap = this.cotaRepository.save(cotap);
			
			cotaProyectoDto.setProyectoId(frac.getProyecto().getId());
			cotaProyectoDto.setFraccionId(frac.getId());
			cotaProyectoDto.setCotaId(cotap.getId());
			
			return cotaProyectoDto; 			
		}
		
		
		// TODO Auto-generated method stub
		return null;
	}

}
