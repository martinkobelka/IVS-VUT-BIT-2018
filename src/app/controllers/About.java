package app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import translator.HaveTranslator;

public class About extends HaveTranslator{

    @FXML
    Button closeButton;

    @FXML
    public void initialize() {
        translate();
    }

    /**
     * Translate all translatable strings in object
     */
    @Override
    public void translate() {

        closeButton.setText(translator.translate("gui", "CLOSE"));

    }

    public void closeButtonAction(ActionEvent actionEvent) {

        // Close acual window
        ((Stage)((Button) actionEvent.getSource()).getScene().getWindow()).close();

    }
}
