package com.github.albertloubet.libraryfx.controller;

import com.github.albertloubet.libraryfx.contract.FormController;
import com.github.albertloubet.libraryfx.enumerator.FontEnum;
import com.github.albertloubet.libraryfx.enumerator.LocalizationEnum;
import com.github.albertloubet.libraryfx.enumerator.ViewEnum;
import com.github.albertloubet.libraryfx.exception.UserNotFoundException;
import com.github.albertloubet.libraryfx.factory.FontFactory;
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
import lombok.SneakyThrows;

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

    private final UserService userService;

    public LoginController() {
        userService = new UserService();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        var fontPoppinsRegular = FontFactory.getFont(FontEnum.POPPINS_REGULAR);
        var fontPoppinsMedium = FontFactory.getFont(FontEnum.POPPINS_MEDIUM);
        var fontPoppinsSemibold = FontFactory.getFont(FontEnum.POPPINS_REGULAR, 36.0);

        labelTitle.setText(getLocalizationText(LocalizationEnum.TITLE));
        labelSubtitle1.setText(getLocalizationText(LocalizationEnum.LOGIN_SUBTITLE_1));
        labelSubtitle2.setText(getLocalizationText(LocalizationEnum.LOGIN_SUBTITLE_2));
        labelRestrictedAcess.setText(getLocalizationText(LocalizationEnum.LOGIN_RESTRICTED_ACESS));
        textFieldUsername.setPromptText(getLocalizationText(LocalizationEnum.USER_USERNAME));
        passwordField.setPromptText(getLocalizationText(LocalizationEnum.USER_PASSWORD));
        checkBoxRemember.setText(getLocalizationText(LocalizationEnum.LOGIN_REMEMBER_ME));
        buttonAcess.setText(getLocalizationText(LocalizationEnum.LOGIN_SIGN_IN));

        labelTitle.setFont(fontPoppinsSemibold);
        labelSubtitle1.setFont(fontPoppinsMedium);
        labelSubtitle2.setFont(fontPoppinsMedium);
        labelRestrictedAcess.setFont(fontPoppinsRegular);
        textFieldUsername.setFont(fontPoppinsRegular);
        passwordField.setFont(fontPoppinsRegular);
        checkBoxRemember.setFont(fontPoppinsRegular);
        buttonAcess.setFont(fontPoppinsRegular);
    }

    @SneakyThrows
    private void goToLibrary() {
        ScreenManager.addScreen(ViewEnum.LIBRARY.name(), FXMLLoader.load(ViewEnum.LIBRARY.recoverViewPath()));
        ScreenManager.activate(ViewEnum.LIBRARY.name());
        ScreenManager.setResizable(true);
        ScreenManager.removeScreen(ViewEnum.LOGIN.name());
    }

    @FXML
    public void onAcess() {
        var username = textFieldUsername.getText();
        var password = SecurityUtil.stringToSha512(passwordField.getText());

        disable();

        Platform.runLater(() -> {
            try {
                userService.authenticate(username, password);

                if (checkBoxRemember.isSelected()) {
                    userService.saveUserSession(password);
                }

                goToLibrary();
            } catch (UserNotFoundException e) {
                addWarm(Optional.empty(), getLocalizationText(LocalizationEnum.USER_NOTFOUND));
            } catch (Exception exception) {
                addError(exception,
                        getLocalizationText(LocalizationEnum.LOGIN_ERROR_SUBTITLE),
                        getLocalizationText(LocalizationEnum.LOGIN_ERROR_HEADER));
            } finally {
                enable();
            }
        });
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