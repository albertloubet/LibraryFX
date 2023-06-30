package com.github.albertloubet.libraryfx.foundation;

import com.github.albertloubet.libraryfx.enumerator.LocalizationEnum;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public abstract class ControllerFoundation implements Initializable {

    public String getLocalizationText(@NonNull String key, Object... args) {
        var bundle = ResourceBundle.getBundle("messages", Locale.getDefault());
        return MessageFormat.format(bundle.getString(key), args);
    }

    public Boolean addConfirm(String title, String header, String msg) {
        var alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(msg);

        Optional<ButtonType> result = alert.showAndWait();
        return result.orElseThrow() == ButtonType.OK;
    }

    public void addInfo(Optional<String> title, String msg) {
        var alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(title.orElse(getLocalizationText((LocalizationEnum.INFO.getText()))));
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public void addInfo(String title, String header, String msg) {
        var alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public void addWarm(Optional<String> title, String msg) {
        var alert = new Alert(Alert.AlertType.WARNING);

        alert.setTitle(title.orElse(getLocalizationText(LocalizationEnum.NOTICE.getText())));
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public void addError(String title, String msg) {
        var  alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public void addError(String title, String header, String msg) {
        var alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public static void addError(Exception ex, String subtitle, String header) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(subtitle);
        alert.setContentText(header);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        ex.printStackTrace();
        String exceptionText = sw.toString();

        Label label = new Label("O erro é:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        alert.getDialogPane().setExpandableContent(expContent);
        alert.showAndWait();
    }

    public String inputText(String title, String header, String msg) {
        var dialog = new TextInputDialog(StringUtils.EMPTY);

        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText(msg);

        Optional<String> result = dialog.showAndWait();

        return result.orElse("");
    }

    public String inputDropbox(String title, String header, String msg, List<String> content) {
        var dialog = new ChoiceDialog<>("b", content);

        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText(msg);

        Optional<String> result = dialog.showAndWait();
        return result.orElse(StringUtils.EMPTY);
    }

    public String selectDiretory(Stage stage){
        var chooser = new DirectoryChooser();
        chooser.setTitle("Selecione o diretório");
        var selectedDirectory = chooser.showDialog(stage);

        return Objects.nonNull(selectedDirectory)
                ? selectedDirectory.getAbsolutePath() + File.separator
                : StringUtils.EMPTY;
    }
}