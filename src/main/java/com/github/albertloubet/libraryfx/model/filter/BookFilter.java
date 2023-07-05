package com.github.albertloubet.libraryfx.model.filter;

import lombok.Builder;

@Builder
public record BookFilter(
        String name,
        String code,
        Integer volume
) { }