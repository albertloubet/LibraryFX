package com.github.albertloubet.libraryfx.controller;

import com.github.albertloubet.libraryfx.contract.FormController;
import com.github.albertloubet.libraryfx.enumerator.LocalizationEnum;
import com.github.albertloubet.libraryfx.enumerator.ViewEnum;
import com.github.albertloubet.libraryfx.exception.UserNotFoundException;
import com.github.albertloubet.libraryfx.foundation.ControllerFoundation;
import com.github.albertloubet.libraryfx.manager.ScreenManager;
import com.github.albertloubet.libraryfx.service.UserService;
import com.github.albertloubet.libraryfx.util.SecurityUtil;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginController extends ControllerFoundation implements FormController {

    @FXML
    private Label labelTitle;

    @FXML
    private Label labelSubtitle1;

    @FXML
    private Label labelSubtitle2;

    @FXML
    private Label labelRestrictedAcess;

    @FXML
    private TextField textFieldUsername;

    @FXML
    private PasswordField passwordField;

    @FXML
    private CheckBox checkBoxRemember;

    @FXML
    private Button buttonAcess;

    private UserService userService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelTitle.setText(getLocalizationText(LocalizationEnum.TITLE));
        labelSubtitle1.setText(getLocalizationText(LocalizationEnum.LOGIN_SUBTITLE_1));
        labelSubtitle2.setText(getLocalizationText(LocalizationEnum.LOGIN_SUBTITLE_2));
        labelRestrictedAcess.setText(getLocalizationText(LocalizationEnum.LOGIN_RESTRICTED_ACESS));
        textFieldUsername.setPromptText(getLocalizationText(LocalizationEnum.USER_USERNAME));
        passwordField.setPromptText(getLocalizationText(LocalizationEnum.USER_PASSWORD));
        checkBoxRemember.setText(getLocalizationText(LocalizationEnum.LOGIN_REMEMBER_ME));
        buttonAcess.setText(getLocalizationText(LocalizationEnum.LOGIN_SIGN_IN));

        userService = new UserService();
    }

    @FXML
    public void onAcess() {
        var username = textFieldUsername.getText();
        var password = SecurityUtil.stringToSha512(passwordField.getText());

        try {
            disable();

            userService.authenticate(username, password);

            if (checkBoxRemember.isSelected()) {
                userService.saveUserSession(password);
            }

            ScreenManager.addScreen(ViewEnum.LIBRARY.name(), FXMLLoader.load(ViewEnum.LIBRARY.recoverViewPath()));

            Platform.runLater(() -> {
                ScreenManager.activate(ViewEnum.LIBRARY.name());
                ScreenManager.setResizable(true);
                ScreenManager.removeScreen(ViewEnum.LOGIN.name());
            });
        } catch (UserNotFoundException e) {
            Platform.runLater(() -> addWarm(Optional.empty(), getLocalizationText(LocalizationEnum.USER_NOTFOUND)));
        } catch (Exception e) {
            Platform.runLater(() -> addError(e,
                    getLocalizationText(LocalizationEnum.LOGIN_ERROR_SUBTITLE),
                    getLocalizationText(LocalizationEnum.LOGIN_ERROR_HEADER)));
        } finally {
            enable();
        }
    }

    @Override
    public void disable() {
        textFieldUsername.setDisable(true);
        passwordField.setDisable(true);
        checkBoxRemember.setDisable(true);
        buttonAcess.setDisable(true);
    }

    @Override
    public void enable() {
        textFieldUsername.setDisable(false);
        passwordField.setDisable(false);
        checkBoxRemember.setDisable(false);
        buttonAcess.setDisable(false);
    }
}