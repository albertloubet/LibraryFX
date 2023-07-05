package com.github.albertloubet.libraryfx.controller;

import com.github.albertloubet.libraryfx.enumerator.FontEnum;
import com.github.albertloubet.libraryfx.enumerator.LocalizationEnum;
import com.github.albertloubet.libraryfx.enumerator.ViewEnum;
import com.github.albertloubet.libraryfx.factory.FontFactory;
import com.github.albertloubet.libraryfx.foundation.ControllerFoundation;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class LibraryController extends ControllerFoundation {

    @FXML
    private Button buttonMenuCalendar;

    @FXML
    private Button buttonMenuPerson;

    @FXML
    private Button buttonMenuBook;

    @FXML
    private Button buttonMenuLoan;

    @FXML
    private Button buttonMenuExit;

    @FXML
    private AnchorPane anchorPaneMain;

    private Map<String, Button> menu;

    private Map<String, ViewEnum> views;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        var poppins = FontFactory.getFont(FontEnum.POPPINS_MEDIUM);

        buttonMenuCalendar.setText(getLocalizationText(LocalizationEnum.MENU_CALENDAR));
        buttonMenuPerson.setText(getLocalizationText(LocalizationEnum.MENU_PERSON));
        buttonMenuBook.setText(getLocalizationText(LocalizationEnum.MENU_BOOK));
        buttonMenuLoan.setText(getLocalizationText(LocalizationEnum.MENU_LOAN));
        buttonMenuExit.setText(getLocalizationText(LocalizationEnum.MENU_EXIT));

        buttonMenuCalendar.setFont(poppins);
        buttonMenuPerson.setFont(poppins);
        buttonMenuBook.setFont(poppins);
        buttonMenuLoan.setFont(poppins);
        buttonMenuExit.setFont(poppins);

        menu = Map.of(
                buttonMenuCalendar.getId(), buttonMenuCalendar,
                buttonMenuPerson.getId(), buttonMenuPerson,
                buttonMenuBook.getId(), buttonMenuBook,
                buttonMenuLoan.getId(), buttonMenuLoan,
                buttonMenuExit.getId(), buttonMenuExit
        );

        views = Map.of(
                buttonMenuBook.getId(), ViewEnum.BOOK,
                buttonMenuCalendar.getId(), ViewEnum.CALENDAR,
                buttonMenuLoan.getId(), ViewEnum.LOAN,
                buttonMenuPerson.getId(), ViewEnum.PERSON
        );

        menu.forEach((id, btn) -> btn.setOnMouseClicked(this::onClickMenu));
        activeMenu(buttonMenuCalendar.getId());
    }

    @SneakyThrows
    private void activeItem(String menuId) {
        if (StringUtils.equals(buttonMenuExit.getId(), menuId)) {
            Platform.exit();
        } else {
            var item = (Parent) FXMLLoader.load(views.get(menuId).recoverViewPath());

            anchorPaneMain.getChildren().clear();

            AnchorPane.setBottomAnchor(item, 0.0);
            AnchorPane.setTopAnchor(item, 0.0);
            AnchorPane.setRightAnchor(item, 0.0);
            AnchorPane.setLeftAnchor(item, 0.0);

            anchorPaneMain.getChildren().addAll(item);
        }
    }

    private void activeMenu(String menuId) {
        menu.forEach((id, btn) -> btn.getStyleClass().removeAll("active"));
        menu.get(menuId).getStyleClass().add("active");
        activeItem(menuId);
    }

    private void onClickMenu(Event e) {
        activeMenu(((Button) e.getSource()).getId());
    }
}
