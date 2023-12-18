package com.lotes.lotesbackend.constants;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum TipoLinea {
    RECTA("R", "Recta"), CURVA("C", "Curva"), DIAGONAL("D", "Diagonal");


    private final String codigo;
    private final String nombre;

    TipoLinea(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public static TipoLinea of(String codigo) {
        if (codigo == null)
            return null;
        return Stream.of(TipoLinea.values())
                .filter(t -> t.getCodigo().equals(codigo))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }


}
