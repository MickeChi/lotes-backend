package com.lotes.lotesbackend.utils;

import com.lotes.lotesbackend.dto.CotaDTO;
import com.lotes.lotesbackend.entity.Cota;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProyectoCotaDTOListConverter extends AbstractConverter<List<Cota>, CotaDTO> {
    @Override
    protected CotaDTO convert(List<Cota> cotas) {

        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(FraccionMaps.cotaDTOMap);
        if (cotas != null && cotas.size() > 0) {
            return mapper.map(cotas.get(0), CotaDTO.class);
        }
        return new CotaDTO();
    }

}
