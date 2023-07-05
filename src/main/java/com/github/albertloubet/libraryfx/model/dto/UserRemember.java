package com.github.albertloubet.libraryfx.model.dto;

import lombok.Builder;

@Builder
public record UserRemember(
        String username,
        String password
) { }