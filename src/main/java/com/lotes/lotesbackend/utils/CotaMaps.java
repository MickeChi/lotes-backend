package com.lotes.lotesbackend.utils;

import com.lotes.lotesbackend.dto.CotaDTO;
import com.lotes.lotesbackend.entity.Cota;
import org.modelmapper.PropertyMap;

import java.util.ArrayList;

public class CotaMaps {
    public static PropertyMap<Cota, CotaDTO> cotaDTOMap = new PropertyMap <Cota, CotaDTO>() {
        protected void configure() {
            map().setFraccionId(source.getFraccion().getId());
            skip().setFraccion(null);
            using(new CotaColindanciaDTOListConvert()).map(source.getColindancias()).setColindancias(null);
            //map().setColindanciasIds(source.getColindancias().stream().map(Fraccion::getId).collect(Collectors.toList()));
            skip().setColindanciasIds(null);
        }
    };
}
