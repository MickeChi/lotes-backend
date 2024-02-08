package com.lotes.lotesbackend.constants;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;


@Converter(autoApply = true)
public class UsoConverter implements AttributeConverter<Uso, String> {

    @Override
    public String convertToDatabaseColumn(Uso uso) {
        if (uso == null)
            return null;
        return uso.toString();
    }

    @Override
    public Uso convertToEntityAttribute(String codigo) {
        if (codigo == null)
            return null;
        return Uso.of(codigo);
    }
}
