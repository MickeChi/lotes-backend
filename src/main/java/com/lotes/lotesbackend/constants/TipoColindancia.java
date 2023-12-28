package com.lotes.lotesbackend.constants;

import java.util.stream.Stream;

import lombok.Getter;

@Getter
public enum TipoColindancia {
	PARCELA("Parcela"), VIALIDAD("Vialidad"), LOTE("Lote");
	
	private String tipo;
	
	private TipoColindancia(String tipo) {
		this.tipo = tipo;
	}
	
	public static TipoColindancia of(String codigo) {
        if (codigo == null)
            return null;
        return Stream.of(TipoColindancia.values())
                .filter(t -> t.toString().equals(codigo))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }
	
	

}
