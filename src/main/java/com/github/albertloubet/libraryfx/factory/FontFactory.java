package com.github.albertloubet.libraryfx.factory;

import com.github.albertloubet.libraryfx.Program;
import com.github.albertloubet.libraryfx.enumerator.FontEnum;
import javafx.scene.text.Font;
import lombok.NonNull;

import java.util.Objects;

public class FontFactory {

    private static final Double STANTARD_SIZE = 13.0;

    public static Font getFont(@NonNull FontEnum fontEnum) {
        return recoverFont(fontEnum, STANTARD_SIZE);
    }

    public static Font getFont(@NonNull FontEnum fontEnum, @NonNull Double size) {
        return recoverFont(fontEnum, size);
    }

    private static Font recoverFont(FontEnum font, Double size) {
        return Font.loadFont(
                Objects.requireNonNull(Program.class.getResource(font.getName())).toExternalForm(),
                size);
    }
}