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

import com.lotes.lotesbackend.dto.FraccionExternaDTO;
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
		this.modelMapper.addMappings(FraccionMaps.fraccionExternaDtoMap);

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
			List<FraccionExternaDTO> listFraccionesExt = this.fraccionRepository.findByProyectoIdAndColindanciaProyecto(proyOp.get().getId(), true)
					.stream().map(p-> this.modelMapper.map(p, FraccionExternaDTO.class)).collect(Collectors.toList());
			ProyectoDTO proyDto = this.modelMapper.map(proyOp.get(), ProyectoDTO.class);
			proyDto.setFraccionesExternas(listFraccionesExt);
			proyectoDtoOp = Optional.of(proyDto); 
		}
		return proyectoDtoOp;
	}

	@Override
	public ProyectoDTO save(ProyectoDTO proyectoDto) {
		
		Proyecto proy = this.proyectoRepository.save(this.modelMapper.map(proyectoDto, Proyecto.class));
		
		List<FraccionExternaDTO> fraccionExternasDto =  proyectoDto.getFraccionesExternas().stream().map(cp -> {
			Fraccion frac = new Fraccion();
			frac.setDescripcion(cp.getDescripcion());
			frac.setProyecto(proy);
			frac.setColindanciaProyecto(cp.isColindanciaProyecto());


			frac = this.fraccionRepository.save(frac);
			
			Cota cotap = new Cota();
			cotap.setColindancias(new ArrayList<>());
			cotap.setFraccion(frac);
			cotap.setMedida(cp.getMedida());
			cotap.setOrden(cp.getOrden());
			cotap.setOrientacion(cp.getOrientacion());
			cotap.setTipoLinea(cp.getTipoLinea());
			cotap = this.cotaRepository.save(cotap);
			
			cp.setProyectoId(frac.getProyecto().getId());
			cp.setFraccionId(frac.getId());
			cp.setCotaId(cotap.getId());
			
			return cp;
		}).collect(Collectors.toList());
		
		proyectoDto = this.modelMapper.map(proy, ProyectoDTO.class);
		proyectoDto.setFraccionesExternas(fraccionExternasDto);
		
		return proyectoDto;
	}

	@Override
	public ProyectoDTO update(ProyectoDTO proyectoDto) {
		
		Optional<Proyecto> proyOp = this.proyectoRepository.findById(proyectoDto.getId());
		if(proyOp.isPresent()) {
			 Proyecto proy = this.proyectoRepository.save(this.modelMapper.map(proyectoDto, Proyecto.class));
			 //proyectoDto = this.modelMapper.map(proy, ProyectoDTO.class);

			List<FraccionExternaDTO> fraccionExternasDto = proyectoDto.getFraccionesExternas().stream().map(cp -> {
				Fraccion frac = new Fraccion();
				if(cp.getFraccionId() != null){
					Optional<Fraccion> fracOp = this.fraccionRepository.findById(cp.getFraccionId());
					if (fracOp.isPresent()){
						frac = fracOp.get();
					}
				}
				frac.setDescripcion(cp.getDescripcion());
				frac.setProyecto(proy);
				frac.setColindanciaProyecto(cp.isColindanciaProyecto());

				frac = this.fraccionRepository.save(frac);

				Cota cotap = new Cota();
				if(cp.getCotaId() != null){
					Optional<Cota> cotaOp = this.cotaRepository.findById(cp.getCotaId());
					if (cotaOp.isPresent()){
						cotap = cotaOp.get();
					}
				}

				cotap.setColindancias(new ArrayList<>());
				cotap.setFraccion(frac);
				cotap.setMedida(cp.getMedida());
				cotap.setOrden(cp.getOrden());
				cotap.setOrientacion(cp.getOrientacion());
				cotap.setTipoLinea(cp.getTipoLinea());
				cotap = this.cotaRepository.save(cotap);

				cp.setProyectoId(frac.getProyecto().getId());
				cp.setFraccionId(frac.getId());
				cp.setCotaId(cotap.getId());

				return cp;
			}).collect(Collectors.toList());

			proyectoDto = this.modelMapper.map(proy, ProyectoDTO.class);
			proyectoDto.setFraccionesExternas(fraccionExternasDto);

		}		
		
		return proyectoDto;
		
	}

	@Override
	public FraccionExternaDTO saveFraccionExterna(FraccionExternaDTO fraccionExternaDto) {
		Optional<Proyecto> proyOp = this.proyectoRepository.findById(fraccionExternaDto.getProyectoId());
		if(proyOp.isPresent()) {
			Fraccion frac = new Fraccion();
			frac.setDescripcion(fraccionExternaDto.getDescripcion());
			frac.setProyecto(proyOp.get());
			
			frac = this.fraccionRepository.save(frac);
			
			Cota cotap = new Cota();
			cotap.setColindancias(new ArrayList<>());
			cotap.setFraccion(frac);
			cotap.setMedida(fraccionExternaDto.getMedida());
			cotap.setOrden(fraccionExternaDto.getOrden());
			cotap.setOrientacion(fraccionExternaDto.getOrientacion());
			cotap.setTipoLinea(fraccionExternaDto.getTipoLinea());
			cotap = this.cotaRepository.save(cotap);
			
			fraccionExternaDto.setProyectoId(frac.getProyecto().getId());
			fraccionExternaDto.setFraccionId(frac.getId());
			fraccionExternaDto.setCotaId(cotap.getId());
			
			return fraccionExternaDto;
		}
		
		
		// TODO Auto-generated method stub
		return null;
	}

}
