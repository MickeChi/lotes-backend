package com.lotes.lotesbackend.constants;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum TipoOperacion {
	CREATE(1),
	UPDATE(2),
	DELETE(3),
	GENERATE(4);

	private Integer tipo;

	private TipoOperacion(Integer tipo) {
		this.tipo = tipo;
	}
	
	public static TipoOperacion of(String codigo) {
        if (codigo == null)
            return null;
        return Stream.of(TipoOperacion.values())
                .filter(t -> t.toString().equals(codigo))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

	public static TipoOperacion of(Integer tipoValue) {
		if (tipoValue == null)
			return null;
		return Stream.of(TipoOperacion.values())
				.filter(t -> t.getTipo() == tipoValue)
				.findFirst().orElseThrow(IllegalArgumentException::new);
	}
	
	

}
