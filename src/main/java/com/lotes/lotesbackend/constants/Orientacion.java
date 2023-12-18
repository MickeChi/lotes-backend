package com.lotes.lotesbackend.constants;

import lombok.Getter;

import java.util.stream.Stream;
@Getter
public enum Orientacion {
    NORTE("N", "Norte"),
    SUR("S", "Sur"),
    ESTE("E", "Este"),
    OESTE("O", "Oeste"),
    NOROESTE("NO", "Noroeste"),
    NORESTE("NE", "Noreste"),
    SUROESTE("SO", "Suroeste"),
    SURESTE("SE", "Sureste");

    private String codigo;
    private String nombre;
    private

    Orientacion(String codigo, String nombre ){
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public static Orientacion of(String codigo){
        return Stream.of(Orientacion.values())
                .filter(o -> o.getCodigo().equals(codigo))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
