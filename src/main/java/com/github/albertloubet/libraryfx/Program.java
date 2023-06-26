package com.github.albertloubet.libraryfx;

import com.github.albertloubet.libraryfx.enumerator.ApplicationEnum;
import com.github.albertloubet.libraryfx.enumerator.ViewEnum;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Program extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        var root = FXMLLoader.load(ViewEnum.LOGIN.recoverViewPath());
        var scene = new Scene((Parent) root);

        primaryStage.setTitle(ApplicationEnum.name.getAtrribute());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}