package com.github.albertloubet.libraryfx.enumerator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApplicationEnum {

    name("Biblioteca FX"),
    version("1.0.0");

    private final String atrribute;
}
