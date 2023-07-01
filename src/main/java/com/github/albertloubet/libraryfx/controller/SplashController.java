package com.github.albertloubet.libraryfx.controller;

import com.github.albertloubet.libraryfx.enumerator.FileEnum;
import com.github.albertloubet.libraryfx.enumerator.LocalizationEnum;
import com.github.albertloubet.libraryfx.enumerator.ViewEnum;
import com.github.albertloubet.libraryfx.foundation.ControllerFoundation;
import com.github.albertloubet.libraryfx.manager.ScreenManager;
import com.github.albertloubet.libraryfx.service.UserService;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.SneakyThrows;

import java.io.File;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SplashController extends ControllerFoundation {

    @FXML
    private Label labelLoading;

    private UserService userService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelLoading.setText(getLocalizationText(LocalizationEnum.LOADING));

        userService = new UserService();

        var timeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> onInit()));
        Platform.runLater(timeline::play);
    }

    private void onInit() {
        var rememberFile = new File(FileEnum.REMEMBER.getName());

        if (rememberFile.exists()) {
            userService.recoverUserSession();
            navigateTo(ViewEnum.LIBRARY, true);
        } else {
            navigateTo(ViewEnum.LOGIN, false);
        }
    }

    @SneakyThrows
    public void navigateTo(ViewEnum view, boolean resizable) {
        var root = FXMLLoader.load(view.recoverViewPath());
        var scene = new Scene((Parent) root);
        var stage = new Stage();

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/assets/css/app.css")).toString());
        stage.setScene(scene);
        stage.setResizable(resizable);
        stage.setTitle(getLocalizationText(LocalizationEnum.TITLE));

        ScreenManager.closeStage();
        ScreenManager.removeScreen(ViewEnum.SPLASH.name());
        ScreenManager.addScreen(view.name(), root);
        ScreenManager.setScene(scene);
        ScreenManager.setStage(stage);

        stage.show();
    }
}
