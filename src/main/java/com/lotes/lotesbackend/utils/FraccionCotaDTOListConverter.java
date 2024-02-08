package com.lotes.lotesbackend.utils;

import com.lotes.lotesbackend.dto.CotaDTO;
import com.lotes.lotesbackend.entity.Cota;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FraccionCotaDTOListConverter extends AbstractConverter<List<Cota>, List<CotaDTO>> {
    @Override
    protected List<CotaDTO> convert(List<Cota> cotas) {

        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(FraccionMaps.cotaDTOMap);
        if (cotas != null) {
            return cotas.stream().map(c -> mapper.map(c, CotaDTO.class)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

}
