package com.github.albertloubet.libraryfx.enumerator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FontEnum {

    POPPINS_REGULAR("/assets/fonts/poppins_regular.ttf"),
    POPPINS_MEDIUM("/assets/fonts/poppins_medium.ttf"),
    POPPINS_SEMIBOLD("/assets/fonts/poppins_semibold.ttf");

    private final String name;
}