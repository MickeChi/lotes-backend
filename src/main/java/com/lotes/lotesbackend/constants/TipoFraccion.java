package com.lotes.lotesbackend.constants;

import java.util.stream.Stream;

import lombok.Getter;

@Getter
public enum TipoFraccion {
	PARCELA("Parcela"), VIALIDAD("Vialidad"), LOTE("Lote");
	
	private String tipo;
	
	private TipoFraccion(String tipo) {
		this.tipo = tipo;
	}
	
	public static TipoFraccion of(String codigo) {
        if (codigo == null)
            return null;
        return Stream.of(TipoFraccion.values())
                .filter(t -> t.toString().equals(codigo))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }
	
	

}
