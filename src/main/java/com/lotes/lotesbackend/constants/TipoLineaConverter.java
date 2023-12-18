package com.lotes.lotesbackend.constants;

import com.lotes.lotesbackend.constants.TipoLinea;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Locale;
import java.util.stream.Stream;
@Converter(autoApply = true)
public class TipoLineaConverter implements AttributeConverter<TipoLinea, String> {

    @Override
    public String convertToDatabaseColumn(TipoLinea tipoLinea) {
        if (tipoLinea == null)
            return null;
        return tipoLinea.getCodigo();
    }

    @Override
    public TipoLinea convertToEntityAttribute(String codigo) {
        if (codigo == null)
            return null;
        return TipoLinea.of(codigo);
    }
}
