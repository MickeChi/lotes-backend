package com.lotes.lotesbackend.constants;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;


@Converter(autoApply = true)
public class TipoFraccionConverter implements AttributeConverter<TipoFraccion, String> {

    @Override
    public String convertToDatabaseColumn(TipoFraccion tipoFraccion) {
        if (tipoFraccion == null)
            return null;
        return tipoFraccion.toString();
    }

    @Override
    public TipoFraccion convertToEntityAttribute(String codigo) {
        if (codigo == null)
            return null;
        return TipoFraccion.of(codigo);
    }
}
