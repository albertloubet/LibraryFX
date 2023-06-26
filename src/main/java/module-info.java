module com.github.albertloubet.libraryfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;

    opens com.github.albertloubet.libraryfx.controller to javafx.fxml;

    exports com.github.albertloubet.libraryfx;
}