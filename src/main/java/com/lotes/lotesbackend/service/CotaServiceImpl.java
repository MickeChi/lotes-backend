package com.lotes.lotesbackend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.lotes.lotesbackend.repository.FraccionRepository;
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

@Service
public class CotaServiceImpl implements CotaService{

	@Autowired
	CotaRepository cotaRepository;

	@Autowired
	FraccionRepository fraccionRepository;

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
	public CotaDTO save(CotaDTO cotaDto) {
		Cota cota = this.modelMapper.map(cotaDto, Cota.class);
		//List<Fraccion> colindancias = cotaDto.getColindancias().stream().map(c -> fraccionRepository.findById(c).get()).toList();
		//cota.setColindancias(colindancias);

		cota = this.cotaRepository.save(cota);

		/*TypeMap<Cota, CotaDTO> propertyMapper = this.modelMapper.createTypeMap(Cota.class, CotaDTO.class);
		propertyMapper.addMappings(
				mapper -> mapper.map(src -> src.getFraccion().getId(), CotaDTO::setFraccionId)
		);*/
		/*propertyMapper.addMappings(
				mapper -> mapper.map(src -> src.getColindancias().stream().map(Fraccion::getId).toList(), CotaDTO::setColindancias)
		);*/

		return this.modelMapper.map(cota, CotaDTO.class);
	}

	@Override
	public CotaDTO update(CotaDTO cotaDto) {
		Optional<Cota> fracOp = this.cotaRepository.findById(cotaDto.getId());
		if(fracOp.isPresent()) {
			Cota cota = this.cotaRepository.save(this.modelMapper.map(cotaDto, Cota.class));
			cotaDto = this.modelMapper.map(cota, CotaDTO.class);
		}

		return cotaDto;
	}

}
