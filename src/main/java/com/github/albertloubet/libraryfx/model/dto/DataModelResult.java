package com.github.albertloubet.libraryfx.model.dto;

import com.github.albertloubet.libraryfx.foundation.EntityFoundation;

import java.util.List;

public record DataModelResult<E extends EntityFoundation>(
        List<E> items,
        Integer total
) { }