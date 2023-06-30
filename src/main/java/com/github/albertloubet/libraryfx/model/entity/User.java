package com.github.albertloubet.libraryfx.model.entity;

import com.github.albertloubet.libraryfx.enumerator.ProfileEnum;
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
public class User extends EntityFoundation {

    private String name;
    private String username;
    private String password;
    private ProfileEnum profile;
}