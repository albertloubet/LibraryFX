package com.github.albertloubet.libraryfx.controller;

import com.github.albertloubet.libraryfx.enumerator.FontEnum;
import com.github.albertloubet.libraryfx.enumerator.LocalizationEnum;
import com.github.albertloubet.libraryfx.factory.FontFactory;
import com.github.albertloubet.libraryfx.foundation.ControllerFoundation;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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

    private Map<String, Button> menu;

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

        menu.forEach((id, btn) -> btn.setOnMouseClicked(this::onClickMenu));
        activeMenu(buttonMenuCalendar.getId());
    }

    private void activeMenu(String menuId) {
        menu.forEach((id, btn) -> btn.getStyleClass().removeAll("active"));
        menu.get(menuId).getStyleClass().add("active");
    }

    private void onClickMenu(Event e) {
        var menuId = ((Button) e.getSource()).getId();
        var menuButton = menu.get(menuId);

        activeMenu(menuId);

        if (menuButton.equals(buttonMenuCalendar)) {
            System.out.println("test");
        } else if (menuButton.equals(buttonMenuPerson)) {
            System.out.println("test");
        } else if (menuButton.equals(buttonMenuBook)) {
            System.out.println("test");
        } else if (menuButton.equals(buttonMenuLoan)) {
            System.out.println("test");
        } else if (menuButton.equals(buttonMenuExit)) {
            Platform.exit();
        }
    }
}
