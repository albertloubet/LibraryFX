package com.github.albertloubet.libraryfx.model.entity;

import com.github.albertloubet.libraryfx.foundation.EntityFoundation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Loan extends EntityFoundation {

    private User manager;
    private Person requester;
    private LocalDate returnAt;
}