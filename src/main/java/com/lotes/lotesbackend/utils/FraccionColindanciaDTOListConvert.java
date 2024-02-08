package com.lotes.lotesbackend.utils;

import com.lotes.lotesbackend.entity.Fraccion;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FraccionColindanciaDTOListConvert extends AbstractConverter<List<Fraccion>, List<Long>> {

    @Override
    protected List<Long> convert(List<Fraccion> fracciones) {
        if (fracciones != null){
            return fracciones.stream().map(Fraccion::getId).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
