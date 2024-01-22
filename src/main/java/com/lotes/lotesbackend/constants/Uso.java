package com.lotes.lotesbackend.constants;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum Uso {
    //[HABITACIONAL, COMERCIAL, COMUN]--LISTO
    HABITACIONAL("Habitacional"), COMERCIAL("Comercial"), COMUN("Común");

    private String tipo;

    private Uso(String tipo) {
        this.tipo = tipo;
    }

    public static Uso of(String codigo) {
        if (codigo == null)
            return null;
        return Stream.of(Uso.values())
                .filter(t -> t.toString().equals(codigo))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }


}
