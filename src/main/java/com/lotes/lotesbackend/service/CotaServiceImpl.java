package com.lotes.lotesbackend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.lotes.lotesbackend.constants.TipoEntidad;
import com.lotes.lotesbackend.constants.TipoOperacion;
import com.lotes.lotesbackend.dto.FraccionDTO;
import com.lotes.lotesbackend.entity.Fraccion;
import com.lotes.lotesbackend.entity.Proyecto;
import com.lotes.lotesbackend.repository.FraccionRepository;
import com.lotes.lotesbackend.repository.OperacionRepository;
import com.lotes.lotesbackend.utils.CotaMaps;
import com.lotes.lotesbackend.utils.FraccionMaps;
import com.lotes.lotesbackend.utils.GenericMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lotes.lotesbackend.dto.CotaDTO;
import com.lotes.lotesbackend.entity.Cota;
import com.lotes.lotesbackend.repository.CotaRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CotaServiceImpl implements CotaService{

	@Autowired
	CotaRepository cotaRepository;

	@Autowired
	FraccionRepository fraccionRepository;

	@Autowired
	private OperacionRepository operacionRepository;

	private final ModelMapper modelMapper;

	public CotaServiceImpl() {
		this.modelMapper = GenericMapper.getMapper();
		this.modelMapper.addMappings(CotaMaps.cotaDTOMap);
	}

	@Override
	public List<CotaDTO> findAll() {
		return this.cotaRepository.findAll().stream()
				.map(p-> this.modelMapper.map(p, CotaDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public Optional<CotaDTO> findById(Long id) {
		Optional<CotaDTO> cotaDtoOp = Optional.empty();
		Optional<Cota> cotaOp = this.cotaRepository.findById(id);
		if(cotaOp.isPresent()) {
			cotaDtoOp = Optional.of(this.modelMapper.map(cotaOp.get(), CotaDTO.class));
		}
		return cotaDtoOp;
	}

	@Override
	@Transactional
	public CotaDTO save(CotaDTO cotaDto) {
		Cota cota = this.modelMapper.map(cotaDto, Cota.class);
		cota.setFraccion(this.fraccionRepository.findById(cotaDto.getFraccionId()).get());
		List<Fraccion> colindancias = cotaDto.getColindanciasIds().stream().map(c -> fraccionRepository.findById(c).get()).toList();
		cota.setColindancias(colindancias);
		cota = this.cotaRepository.save(cota);

		this.operacionRepository.saveOperacion(TipoOperacion.CREATE, TipoEntidad.COTA, 1L, "cotaId: " + cota.getId());


		return this.modelMapper.map(cota, CotaDTO.class);
	}

	@Override
	@Transactional
	public CotaDTO update(CotaDTO cotaDto) {
		Cota cota = this.modelMapper.map(cotaDto, Cota.class);
		cota.setFraccion(this.fraccionRepository.findById(cotaDto.getFraccionId()).get());
		List<Fraccion> colindancias = cotaDto.getColindanciasIds().stream().map(c -> fraccionRepository.findById(c).get()).toList();
		cota.setColindancias(colindancias);
		cota = this.cotaRepository.save(cota);

		this.operacionRepository.saveOperacion(TipoOperacion.UPDATE, TipoEntidad.COTA, 1L, "cotaId: " + cota.getId());

		return this.modelMapper.map(cota, CotaDTO.class);
	}

	@Override
	public List<CotaDTO> getCotasByFraccionId(Long fraccionId) {
		List<CotaDTO> respuesta = new ArrayList<>();
		Optional<Fraccion> fracOp = this.fraccionRepository.findById(fraccionId);
		if(fracOp.isPresent()) {
			respuesta = this.cotaRepository.getCotasByFraccionId(fraccionId).stream()
					.map(p-> {
						return this.modelMapper.map(p, CotaDTO.class);
					})
					.collect(Collectors.toList());
		}

		return respuesta;
	}
}
