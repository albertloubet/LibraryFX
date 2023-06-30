package com.github.albertloubet.libraryfx.enumerator;

import lombok.AllArgsConstructor;

import java.net.URL;
import java.util.Objects;

@AllArgsConstructor
public enum ViewEnum {
    SPLASH("splash", "/views/splash.fxml"),
    LOGIN("login", "/views/login.fxml"),
    LIBRARY("library", "/views/library.fxml");

    private final String name;
    private final String path;

    public URL recoverViewPath() {
        return Objects.requireNonNull(getClass().getResource(path));
    }
}