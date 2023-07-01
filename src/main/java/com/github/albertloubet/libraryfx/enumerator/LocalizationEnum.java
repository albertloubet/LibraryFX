package com.github.albertloubet.libraryfx.enumerator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LocalizationEnum {

    TITLE("title"),
    LOADING("loading"),
    INFO("info"),
    NOTICE("notice"),

    LOGIN_TITLE("login.title"),
    LOGIN_SUBTITLE_1("login.subtitle1"),
    LOGIN_SUBTITLE_2("login.subtitle2"),
    LOGIN_RESTRICTED_ACESS("login.restrictedacess"),
    LOGIN_REMEMBER_ME("login.rememberme"),
    LOGIN_SIGN_IN("login.signin"),
    LOGIN_ERROR_HEADER("login.error.header"),
    LOGIN_ERROR_SUBTITLE("login.error.subtitle"),

    USER_USERNAME("user.username"),
    USER_PASSWORD("user.password"),
    USER_NOTFOUND("user.notfound"),

    MENU_CALENDAR("menu.calendar"),
    MENU_PERSON("menu.person"),
    MENU_BOOK("menu.book"),
    MENU_LOAN("menu.loan"),
    MENU_EXIT("menu.exit");

    private final String text;
}
