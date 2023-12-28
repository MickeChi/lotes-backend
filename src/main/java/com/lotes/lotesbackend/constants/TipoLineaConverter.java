package com.lotes.lotesbackend.constants;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

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
