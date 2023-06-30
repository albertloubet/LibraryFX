package com.github.albertloubet.libraryfx.foundation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public abstract class EntityFoundation {

    protected Long id;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
}