package com.lotes.lotesbackend.constants;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;


@Converter(autoApply = true)
public class TipoColindanciaConverter implements AttributeConverter<TipoColindancia, String> {

    @Override
    public String convertToDatabaseColumn(TipoColindancia tipoColindancia) {
        if (tipoColindancia == null)
            return null;
        return tipoColindancia.toString();
    }

    @Override
    public TipoColindancia convertToEntityAttribute(String codigo) {
        if (codigo == null)
            return null;
        return TipoColindancia.of(codigo);
    }
}
