package com.lotes.lotesbackend.constants;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OrientacionConverter implements AttributeConverter<Orientacion, String> {
    @Override
    public String convertToDatabaseColumn(Orientacion orientacion) {
        if (orientacion == null)
            return null;
        return orientacion.getCodigo();
    }

    @Override
    public Orientacion convertToEntityAttribute(String codigo) {
        if (codigo == null)
            return null;
        return Orientacion.of(codigo);
    }
}
