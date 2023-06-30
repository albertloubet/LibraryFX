package com.github.albertloubet.libraryfx.util;

import lombok.SneakyThrows;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class SecurityUtil {

    @SneakyThrows
    public static String stringToSha512(String text) {
        var digest = MessageDigest.getInstance("SHA-512");
        digest.reset();
        digest.update(text.getBytes(StandardCharsets.UTF_8));
        return String.format("%0128x", new BigInteger(1, digest.digest()));
    }
}
