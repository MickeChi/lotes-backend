package com.lotes.lotesbackend.repository;

import com.lotes.lotesbackend.constants.TipoEntidad;
import com.lotes.lotesbackend.constants.TipoIndicador;
import com.lotes.lotesbackend.constants.TipoOperacion;
import com.lotes.lotesbackend.entity.IndicadorOperacion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;

import java.util.ArrayList;
import java.util.List;
public class CustomOperacionRepositoryImpl implements CustomOperacionRepository {
    private final static Integer SUCCESS_CODE = 1;
    private final static Integer ERROR_CODE = 0;
    @PersistenceContext
    private EntityManager entityManager;

    /*public CustomOperacionRepositoryImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }*/

    @Override
    public Long saveOperacion(TipoOperacion tipoOperacion, TipoEntidad tipoEntidad, Long usuarioId, String datos) {

        StoredProcedureQuery spq = entityManager.createStoredProcedureQuery("proyecto_lotes.spCrearOperacion");
        spq.registerStoredProcedureParameter("P_TIPO_OPERACION_ID", Long.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("P_TIPO_ENTIDAD_ID", Long.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("P_USUARIO_ID", Long.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("P_DATOS", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("P_OPERACION_ID", Long.class, ParameterMode.OUT);
        spq.setParameter("P_TIPO_OPERACION_ID", tipoOperacion.getTipo());
        spq.setParameter("P_TIPO_ENTIDAD_ID", tipoEntidad.getTipo());
        spq.setParameter("P_USUARIO_ID", usuarioId);
        spq.setParameter("P_DATOS", datos);

        spq.execute();
        Long operacionId = (Long) spq.getOutputParameterValue("P_OPERACION_ID");
        return operacionId;

    }

    @Override
    public List<IndicadorOperacion> getIndicadoresOperaciones(Long usuarioId, TipoIndicador tipoIndicador) {

        List<IndicadorOperacion> indicadores = new ArrayList<>();

        StoredProcedureQuery spq = entityManager.createStoredProcedureQuery("proyecto_lotes.spIndicadoresOperaciones", "IndicadorMapping");
        spq.registerStoredProcedureParameter("P_USUARIO_ID", Long.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("P_TIPO_INDICADOR", Integer.class, ParameterMode.IN);
        spq.setParameter("P_USUARIO_ID", usuarioId);
        spq.setParameter("P_TIPO_INDICADOR", tipoIndicador.getTipo());

        spq.execute();
        indicadores = spq.getResultList();

        return indicadores;
    }
}
