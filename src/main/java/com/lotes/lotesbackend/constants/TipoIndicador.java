package com.lotes.lotesbackend.constants;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum TipoIndicador {
	POR_USUARIO(1),
	POR_USUARIO_ENTIDAD(2),
	POR_USUARIO_OPERACION(3),
	POR_TODOS(4),
	POR_ENTIDAD(5),
	POR_OPERACION(6);

	private Integer tipo;

	private TipoIndicador(Integer tipo) {
		this.tipo = tipo;
	}
	
	public static TipoIndicador of(String codigo) {
        if (codigo == null)
            return null;
        return Stream.of(TipoIndicador.values())
                .filter(t -> t.toString().equals(codigo))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

	public static TipoIndicador of(Integer tipoValue) {
		if (tipoValue == null)
			return null;
		return Stream.of(TipoIndicador.values())
				.filter(t -> t.getTipo() == tipoValue)
				.findFirst().orElseThrow(IllegalArgumentException::new);
	}
	
	

}
