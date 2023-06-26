package com.github.albertloubet.libraryfx.enumerator;

import lombok.AllArgsConstructor;

import java.net.URL;
import java.util.Objects;

@AllArgsConstructor
public enum ViewEnum {
    LOGIN("/views/login.fxml");

    private final String path;

    public URL recoverViewPath() {
        return Objects.requireNonNull(getClass().getResource(path));
    }
}