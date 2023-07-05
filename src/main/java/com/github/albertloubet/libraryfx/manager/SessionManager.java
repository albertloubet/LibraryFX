package com.github.albertloubet.libraryfx.manager;

import com.github.albertloubet.libraryfx.enumerator.ProfileEnum;
import com.github.albertloubet.libraryfx.model.entity.User;

import java.util.Objects;

public final class SessionManager {

    private static User userLogged;

    public static void setUserLogged(User userLogged) {
        SessionManager.userLogged = userLogged;
    }

    public static Long getIdUserLogged() {
        return userLogged.getId();
    }

    public static String getNameUserLogged() {
        return userLogged.getName();
    }

    public static String getUsernameLogged() {
        return userLogged.getUsername();
    }

    public static ProfileEnum getProfileFromUserLogged() {
        return userLogged.getProfile();
    }

    public static boolean hasUser() {
        return Objects.nonNull(userLogged);
    }
}