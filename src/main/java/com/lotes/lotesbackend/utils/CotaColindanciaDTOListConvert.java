package com.lotes.lotesbackend.utils;

import com.lotes.lotesbackend.dto.FraccionDTO;
import com.lotes.lotesbackend.entity.Fraccion;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CotaColindanciaDTOListConvert extends AbstractConverter<List<Fraccion>, List<FraccionDTO>> {

    @Override
    protected List<FraccionDTO> convert(List<Fraccion> fracciones) {
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(FraccionMaps.fraccionFlatDtoMap);

        if (fracciones != null){
            return fracciones.stream().map(f -> mapper.map(f, FraccionDTO.class)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
