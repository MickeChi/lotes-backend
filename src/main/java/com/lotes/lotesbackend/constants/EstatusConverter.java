package com.lotes.lotesbackend.constants;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EstatusConverter implements AttributeConverter<Estatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Estatus estatus) {
        if (estatus == null)
            return null;
        return estatus.getEstatus();
    }

    @Override
    public Estatus convertToEntityAttribute(Integer value) {
        if (value == null)
            return null;
        return Estatus.of(value);
    }
}
