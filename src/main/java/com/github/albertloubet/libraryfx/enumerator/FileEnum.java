package com.github.albertloubet.libraryfx.enumerator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FileEnum {

    REMEMBER("remember.json");

    private final String name;
}
