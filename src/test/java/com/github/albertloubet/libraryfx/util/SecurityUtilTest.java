package com.github.albertloubet.libraryfx.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class SecurityUtilTest {

    @Test
    public void stringToSha512() {
        var hashPassword = "dff6599c4c09df9b6d31b7bc7e74fb8290ca698c9c75c614a2a197b1c452efc17a8b07595624fb9b871753a727815f07dde072d6d4a6cae82b0aef6724727fca";
        var defaultPassword = "LibraryFX-Security";

        assertEquals(hashPassword, SecurityUtil.stringToSha512(defaultPassword));
    }
}
