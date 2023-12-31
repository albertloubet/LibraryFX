package com.github.albertloubet.libraryfx.foundation;

import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class RepositoryFoundation {

    private static final String USERNAME = "libraryfx-user";
    private static final String PASSWORD = "20230125";
    private static final String DATABASE = "libraryfx";

    @SneakyThrows
    protected Connection getConnection() {
        Class.forName("org.mariadb.jdbc.Driver");
        var url = String.format("jdbc:mariadb://localhost:3306/%s", DATABASE);
        return DriverManager.getConnection(url, USERNAME, PASSWORD);
    }

    protected void whereOrAnd(StringBuilder query) {
        if (StringUtils.contains(query, "WHERE")) {
            query.append(" AND ");
        } else {
            query.append(" WHERE ");
        }
    }
}