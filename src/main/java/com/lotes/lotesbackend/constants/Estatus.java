package com.lotes.lotesbackend.constants;

import java.util.stream.Stream;

import lombok.Getter;

@Getter
public enum Estatus {
	DESACTIVADO(0),
	ACTIVO(1);
	
	private Integer estatus;
	
	Estatus(Integer estatus) {
		this.estatus = estatus;
	}
	
	public static Estatus of(String codigo) {
        if (codigo == null)
            return null;
        return Stream.of(Estatus.values())
                .filter(t -> t.toString().equals(codigo))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

	public static Estatus of(Integer value) {
		if (value == null)
			return null;
		return Stream.of(Estatus.values())
				.filter(t -> t.getEstatus() == value)
				.findFirst().orElseThrow(IllegalArgumentException::new);
	}

}
