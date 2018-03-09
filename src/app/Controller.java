package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import parser.MyParser;
import parser.antlr_parser.ReturnValue;

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
    public ListView<String> functionSymbolTable;

    @FXML
    public ListView<String> symbolTableFunctions;

    private MyParser myParser;



    @FXML
    public void initialize() {

        URL url = this.getClass().getResource("novy.html");

        visualisation.getEngine().load(url.toString());

        ObservableList<String> items = FXCollections.observableArrayList (
                "E = 2.718281", "PI = 3.141593", "SOUCET = E + PI");
        symbolTableFunctions.setItems(items);

        ObservableList<String> functions = FXCollections.observableArrayList (
                "f(x) = x + 10", "g(x) = x^e", "ahoj(x) = f(x) * g(x)");
        functionSymbolTable.setItems(functions);

        this.myParser = new MyParser();
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

        ReturnValue returnValue;
        try {
            returnValue = myParser.parse(test_action.getText());
            visualisation.getEngine().executeScript(
                    "katex.render(\""+returnValue.getTextRepresentation()+"="
                            +String.valueOf(returnValue.getValue())+"\", element);"
            );
        }
        catch (NullPointerException ex) {
            System.err.println("Neúplný výraz");
        }


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
