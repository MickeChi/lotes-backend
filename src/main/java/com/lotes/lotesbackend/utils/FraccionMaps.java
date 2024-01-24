package com.lotes.lotesbackend.utils;

import com.lotes.lotesbackend.dto.CotaDTO;
import com.lotes.lotesbackend.dto.CotaProyectoDTO;
import com.lotes.lotesbackend.dto.FraccionDTO;
import com.lotes.lotesbackend.entity.Cota;
import com.lotes.lotesbackend.entity.Fraccion;
import org.modelmapper.PropertyMap;

import java.math.BigDecimal;
import java.util.ArrayList;

public class FraccionMaps {

    public static PropertyMap<Fraccion, FraccionDTO> fraccionDtoMap = new PropertyMap<Fraccion, FraccionDTO>() {
        protected void configure() {
            map().setProyectoId(source.getProyecto().getId());
            //map(source.getAddress().city, destination.city);
            //using(new FraccionCotaDTOListConverter()).map(source::getCotas, destination::setCotas);
            skip().setProyecto(null);
            using(new FraccionCotaDTOListConverter()).map(source.getCotas()).setCotas(null);

        }
    };
    
    public static PropertyMap<Fraccion, CotaProyectoDTO> cotaProyDtoMap = new PropertyMap<Fraccion, CotaProyectoDTO>() {
        protected void configure() {
            map().setProyectoId(source.getProyecto().getId());
            map().setFraccionId(source.getId());
            map().setDescripcion(source.getDescripcion());
            using(new ProyectoCotaDTOListConverter()).map(source.getCotas()).setDatosCota(null);
        }
    };

    public static PropertyMap<Fraccion, FraccionDTO> fraccionFlatDtoMap = new PropertyMap<Fraccion, FraccionDTO>() {
        protected void configure() {
            skip().setProyecto(null);
            skip().setCotas(new ArrayList<>());
        }
    };

    public static PropertyMap<Cota, CotaDTO> cotaDTOMap = new PropertyMap <Cota, CotaDTO>() {
        protected void configure() {
            map().setFraccionId(source.getFraccion().getId());
            skip().setFraccion(null);
            using(new FraccionColindanciaDTOListConvert()).map(source.getColindancias()).setColindanciasIds(null);
            //map().setColindanciasIds(source.getColindancias().stream().map(Fraccion::getId).collect(Collectors.toList()));
            skip().setColindancias(new ArrayList<>());
        }
    };

}
