package com.lotes.lotesbackend.service;

import com.lotes.lotesbackend.constants.TipoEntidad;
import com.lotes.lotesbackend.constants.TipoIndicador;
import com.lotes.lotesbackend.constants.TipoOperacion;
import com.lotes.lotesbackend.dto.FraccionDTO;
import com.lotes.lotesbackend.dto.IndicadorOperacionDTO;
import com.lotes.lotesbackend.dto.OperacionDTO;
import com.lotes.lotesbackend.entity.IndicadorOperacion;
import com.lotes.lotesbackend.repository.CustomOperacionRepository;
import com.lotes.lotesbackend.repository.OperacionRepository;
import com.lotes.lotesbackend.utils.GenericMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperacionServiceImpl implements OperacionService {
    private final static Integer SUCCESS_CODE = 1;
    private final static Integer ERROR_CODE = 0;

    @Autowired
    private OperacionRepository operacionRepository;

    private final ModelMapper modelMapper;

    public OperacionServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = GenericMapper.getMapper();
    }

    @Override
    public Long saveOperacion(TipoOperacion tipoOperacion, TipoEntidad tipoEntidad, Long usuarioId, String datos) {

        return this.operacionRepository.saveOperacion(tipoOperacion, tipoEntidad, usuarioId, datos);

    }

    @Override
    public List<IndicadorOperacionDTO> getIndicadoresOperaciones(Long usuarioId, TipoIndicador tipoIndicador) {

        List<IndicadorOperacionDTO> indicadoresDTO = new ArrayList<>();

        List<IndicadorOperacion> indicadores = this.operacionRepository.getIndicadoresOperaciones(usuarioId, tipoIndicador);
        if(indicadores != null && !indicadores.isEmpty()){
            indicadoresDTO = indicadores.stream().map(i -> this.modelMapper.map(i, IndicadorOperacionDTO.class))
                    .collect(Collectors.toList());
        }

        return indicadoresDTO;
    }

    @Override
    public List<OperacionDTO> findAll() {
        return this.operacionRepository.findAll().stream()
                .map(p-> {
                    return this.modelMapper.map(p, OperacionDTO.class);
                })
                .collect(Collectors.toList());
    }
}
