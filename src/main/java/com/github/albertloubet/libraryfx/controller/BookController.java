package com.github.albertloubet.libraryfx.controller;

import com.github.albertloubet.libraryfx.enumerator.FontEnum;
import com.github.albertloubet.libraryfx.enumerator.LocalizationEnum;
import com.github.albertloubet.libraryfx.factory.FontFactory;
import com.github.albertloubet.libraryfx.foundation.ControllerFoundation;
import com.github.albertloubet.libraryfx.model.filter.BookFilter;
import com.github.albertloubet.libraryfx.model.property.BookProperty;
import com.github.albertloubet.libraryfx.service.BookService;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;

public class BookController extends ControllerFoundation {

    @FXML
    private Label labelTitleBook;

    @FXML
    private Label labelTotal;

    @FXML
    private Button buttonAdd;

    @FXML
    private TableColumn<BookProperty, String> tableColumnName;

    @FXML
    private TableColumn<BookProperty, Integer> tableColumnVolume;

    @FXML
    private TableColumn<BookProperty, String> tableColumnCode;

    @FXML
    private TableColumn<BookProperty, Integer> tableColumnQuantity;

    @FXML
    private TableView<BookProperty> tableViewBooks;

    private final BookService bookService;

    public BookController() {
        bookService = new BookService();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        var poppinsRegular = FontFactory.getFont(FontEnum.POPPINS_REGULAR);
        var poppinsTitle = FontFactory.getFont(FontEnum.POPPINS_SEMIBOLD, 24.0);

        labelTitleBook.setText(getLocalizationText(LocalizationEnum.MENU_BOOK));
        buttonAdd.setText(getLocalizationText(LocalizationEnum.BUTTON_ADD));
        tableColumnName.setText(getLocalizationText(LocalizationEnum.BOOK_NAME));
        tableColumnCode.setText(getLocalizationText(LocalizationEnum.BOOK_CODE));
        tableColumnVolume.setText(getLocalizationText(LocalizationEnum.BOOK_VOLUME));
        tableColumnQuantity.setText(getLocalizationText(LocalizationEnum.BOOK_QUANTITY));

        labelTitleBook.setFont(poppinsTitle);
        labelTotal.setFont(poppinsRegular);
        buttonAdd.setFont(poppinsRegular);

        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        tableColumnVolume.setCellValueFactory(new PropertyValueFactory<>("volume"));
        tableColumnQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        Executors.newSingleThreadExecutor().submit(this::load);
    }

    private void load() {
        var tableItems = new ArrayList<BookProperty>();
        var books = bookService.getAll(BookFilter.builder().build());

        Platform.runLater(() -> {
            books.forEach(book ->
                    tableItems.add(BookProperty.builder()
                            .code(new SimpleStringProperty(book.getCode()))
                            .name(new SimpleStringProperty(book.getName()))
                            .volume(new SimpleIntegerProperty(book.getVolume()))
                            .quantity(new SimpleIntegerProperty(book.getQuantity()))
                            .build()));
            tableViewBooks.setItems(FXCollections.observableArrayList(tableItems));
        });
    }
}
