package com.github.albertloubet.libraryfx.model.property;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import lombok.Builder;

@Builder
public record BookProperty(
        StringProperty name,
        StringProperty code,
        IntegerProperty quantity,
        IntegerProperty volume
) {

    public String getName() {
        return name.get();
    }

    public String getCode() {
        return code.get();
    }

    public int getQuantity() {
        return quantity.get();
    }

    public int getVolume() {
        return volume.get();
    }
}