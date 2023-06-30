module com.github.albertloubet.libraryfx {
    requires lombok;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.mariadb.jdbc;
    requires org.apache.commons.lang3;
    requires com.google.gson;

    opens com.github.albertloubet.libraryfx.controller to javafx.fxml;
    opens com.github.albertloubet.libraryfx.model.dto to com.google.gson;

    exports com.github.albertloubet.libraryfx;
}