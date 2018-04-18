package app;

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
import parser.symbol_table.Function;
import parser.symbol_table.TableOfFunctions;
import parser.symbol_table.TableOfVariables;
import parser.symbol_table.Variable;

import java.io.Console;
import java.net.URL;
import java.security.Key;
import java.util.Random;

public class Controller{

    private final String NULL_VALUE = "0";

    @FXML
    public Button button_null;
    @FXML
    public TextField test_action;
    @FXML
    public WebView visualisation;

    @FXML
    public ListView<Function> functionSymbolTable;

    @FXML
    public ListView<Variable> symbolTableFunctions;

    private MyParser myParser;

    private TableOfVariables tableOfVariables;
    private TableOfFunctions tableOfFunctions;

    private int sequence;



    @FXML
    public void initialize() {

        URL url = this.getClass().getResource("novy.html");

        visualisation.getEngine().load(url.toString());

        tableOfVariables = new TableOfVariables();
        tableOfFunctions = new TableOfFunctions();

        ObservableList<Variable> listOfVariables = tableOfVariables.getVariables();
        ObservableList<Function> listOfFunctions = tableOfFunctions.getFunctions();

        symbolTableFunctions.setItems(listOfVariables);
        functionSymbolTable.setItems(listOfFunctions);

        this.myParser = new MyParser(tableOfVariables, tableOfFunctions);
        this.sequence = 0;

        test_action.textProperty().addListener((observable, oldValue, newValue) -> {
            myParser.sedAddVariable(false);
            count();
        });
    }


    @FXML
    public void button_action(ActionEvent event) {

        addCharacter(((Button) event.getSource()).getText());

        myParser.sedAddVariable(false);
    }

    private void addCharacter(String character) {

        String actualText = test_action.getText();

        if(actualText.equals("|")) {
            test_action.setText(character);
        }
        else {
            test_action.setText(actualText + character);
        }
    }

    private void count() {

        ReturnValue returnValue;
        try {

            returnValue = myParser.parse(test_action.getText());

            visualisation.getEngine().executeScript(
                    "telo.innerHTML = \"\";"
            );

            while(returnValue != null) {

                int next = this.sequence++;

                visualisation.getEngine().executeScript(
                        "var nadpis = document.createElement(\"H1\");" +
                                "nadpis.id = \"vyk" + String.valueOf(next) + "\";" +
                                "telo.appendChild(nadpis);" +
                                "var vykresleni = document.getElementById(\"vyk" + String.valueOf(next) + "\");"
                );

                visualisation.getEngine().executeScript(
                        "katex.render(\"" + returnValue.getTextRepresentation() + "="
                                + String.valueOf(returnValue.getValue()) + "\", vykresleni);"
                );

                returnValue = returnValue.getNext();
            }
        }
        catch (NullPointerException ex) {
            System.err.println("Neúplný výraz: ");
        }

    }

    @FXML
    public void send_action(ActionEvent event)  {

        myParser.sedAddVariable(true);
        count();

    }

    @FXML
    public void send_action_button(MouseEvent event) {

        myParser.sedAddVariable(true);
        count();

    }

    @FXML
    public void field_click(MouseEvent event) {
        String actualText = test_action.getText();
        if(actualText.equals("|")) {
            test_action.setText("");
        }
    }

    public void backspace_click(MouseEvent mouseEvent) {

        removeChar();

    }

    private void removeChar() {

        String actualText = test_action.getText();
        int length = actualText.length();
        if(length != 0) {
            test_action.setText(actualText.substring(0, length - 1));
        }



    }

    public void layout_key_press(KeyEvent keyEvent) {

        if (keyEvent.getCode() == KeyCode.ENTER) {
            myParser.sedAddVariable(true);
            count();
        }
        else {

            if (!test_action.isFocused()) {

                String actualText = test_action.getText();

                if (keyEvent.getCode() == KeyCode.BACK_SPACE) {

                    removeChar();
                } else {

                    String firstChar = keyEvent.getText();

                    if (isPrintableCharacter(firstChar)) {
                        addCharacter(firstChar);
                    }

                }
            }

        }

    }

    private boolean isPrintableCharacter(String checkChar) {

        if(checkChar.matches("^[a-zA-Z0-9]|\\.|\\(|\\)|\\+|\\-|\\*|/$|=")) {
            return true;
        }

        return false;

    }

}
