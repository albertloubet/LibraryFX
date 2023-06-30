package com.github.albertloubet.libraryfx.model.entity;

import com.github.albertloubet.libraryfx.enumerator.ActionEnum;
import com.github.albertloubet.libraryfx.foundation.EntityFoundation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Log extends EntityFoundation {

    private User manager;
    private ActionEnum action;
    private String oldEntity;
    private String newEntity;
}