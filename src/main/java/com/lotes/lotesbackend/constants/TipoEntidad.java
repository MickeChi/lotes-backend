package com.lotes.lotesbackend.constants;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum TipoEntidad {
	PROYECTO(1),
	FRACCION(2),
	COTA(3),
	DOCUMENTO_FRACCIONES(4);

	private Integer tipo;

	private TipoEntidad(Integer tipo) {
		this.tipo = tipo;
	}
	
	public static TipoEntidad of(String codigo) {
        if (codigo == null)
            return null;
        return Stream.of(TipoEntidad.values())
                .filter(t -> t.toString().equals(codigo))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

	public static TipoEntidad of(Integer tipoValue) {
		if (tipoValue == null)
			return null;
		return Stream.of(TipoEntidad.values())
				.filter(t -> t.getTipo() == tipoValue)
				.findFirst().orElseThrow(IllegalArgumentException::new);
	}
	
	

}
