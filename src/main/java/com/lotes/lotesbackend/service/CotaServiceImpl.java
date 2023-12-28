package com.lotes.lotesbackend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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
	ModelMapper modelMapper;

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
		Cota cota = this.cotaRepository.save(this.modelMapper.map(cotaDto, Cota.class));
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
