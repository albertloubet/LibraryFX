package com.github.albertloubet.libraryfx;

import com.github.albertloubet.libraryfx.enumerator.ViewEnum;
import com.github.albertloubet.libraryfx.manager.ScreenManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class Program extends Application {

    @Override
    public void start(Stage splashSStage) throws Exception {
        var root = FXMLLoader.load(ViewEnum.SPLASH.recoverViewPath());
        var scene = new Scene((Parent) root);

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/assets/css/app.css")).toString());

        splashSStage.initStyle(StageStyle.UNDECORATED);
        splashSStage.setScene(scene);
        splashSStage.setResizable(false);

        ScreenManager.addScreen(ViewEnum.SPLASH.name(), root);
        ScreenManager.setScene(scene);
        ScreenManager.setStage(splashSStage);

        splashSStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}