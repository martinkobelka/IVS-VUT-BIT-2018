package app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;

import java.io.Console;
import java.net.URL;

public class Controller{

    private final String NULL_VALUE = "0";

    @FXML
    public Button button_null;
    @FXML
    public TextField test_action;
    @FXML
    public WebView visualisation;

    @FXML
    public void initialize() {


        URL url = this.getClass().getResource("novy.html");

        visualisation.getEngine().load(url.toString());
    }


    @FXML
    public void button_action(ActionEvent event) {
        String actualText = test_action.getText();

        if(actualText.equals("|")) {
            test_action.setText(((Button) event.getSource()).getText());
        }
        else {
            test_action.setText(actualText + ((Button) event.getSource()).getText());
        }

        visualisation.getEngine().executeScript(
            "katex.render(\"c = \\\\pm\\\\sqrt{a^2 + b^2}\", element);"
        );

    }

    @FXML
    public void send_action(ActionEvent event)  {

        String actualText = test_action.getText();
        System.out.println("Posílám ke zpracování: " + actualText);

    }

    @FXML
    public void send_action_button(MouseEvent event) {
        String actualText = test_action.getText();
        System.out.println("Posílám ke zpracování: " + actualText);
    }

    @FXML
    public void field_click(MouseEvent event) {
        String actualText = test_action.getText();
        if(actualText.equals("|")) {
            test_action.setText("");
        }
    }

    public void backspace_click(MouseEvent mouseEvent) {
        String actualText = test_action.getText();
        int length = actualText.length();
        if(length != 0) {
            test_action.setText(actualText.substring(0, length - 1));
        }
    }

    public void layout_key_press(KeyEvent keyEvent) {

        if(keyEvent.getCode() == KeyCode.ENTER) {

            String actualText = test_action.getText();
            System.out.println("Posílám ke zpracování: " + actualText);
        }

    }
}
