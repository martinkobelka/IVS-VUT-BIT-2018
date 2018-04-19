/**
 * Copyright 2018 Martin Kobelka (xkobel02@stud.fit.vutbr.cz)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package app;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import parser.MyParser;
import parser.antlr_parser.ReturnValue;
import parser.antlr_parser.TypeReturnValue;
import parser.symbol_table.Function;
import parser.symbol_table.TableOfFunctions;
import parser.symbol_table.TableOfVariables;
import parser.symbol_table.Variable;

import java.net.URL;
import java.text.ParseException;

/**
 * @author Martin Kobelka (xkobel02@stud.fit.vutbr.cz)
 * @version 1.0
 *
 * @brief Constroller for main app view
 */
public class Controller{

    /**
     * Regex for printable character
     */
    private final String PRINTABLE_CHARACTER = "^[a-zA-Z0-9]|\\.|\\(|\\)|\\+|\\-|\\*|/$|=|,|\\^";

    @FXML
    public Button button_null;
    @FXML
    public TextField test_action;
    @FXML
    public WebView visualisation;


    @FXML
    public ListView<Variable> symbolVariableListView;

    @FXML
    public ListView<Function> symbolFunctionsListView;

    /**
     * Parser for parsing expression
     */
    private MyParser myParser;

    /**
     * Run representation of table of variables
     */
    private TableOfVariables tableOfVariables;

    /**
     * Run representation of table of functions
     */
    private TableOfFunctions tableOfFunctions;

    /**
     * Counter of equation which was written
     */
    private int sequence;

    @FXML
    public void initialize() {

        // Get address of render html file
        URL url = this.getClass().getResource("eqRender.html");

        // Load visu
        visualisation.getEngine().load(url.toString());

        // Create tables
        tableOfVariables = new TableOfVariables();
        tableOfFunctions = new TableOfFunctions();

        // Create lists for tables
        ObservableList<Variable> listOfVariables = tableOfVariables.getVariables();
        ObservableList<Function> listOfFunctions = tableOfFunctions.getFunctions();

        // Set ovservalble lists to views
        symbolVariableListView.setItems(listOfVariables);
        symbolFunctionsListView.setItems(listOfFunctions);

        // Create parser
        this.myParser = new MyParser(tableOfVariables, tableOfFunctions);
        this.sequence = 0;

        // Add listener for changing value in text box
        test_action.textProperty().addListener((observable, oldValue, newValue) -> {
            myParser.setAddVariable(false);
            count();
        });
    }


    @FXML
    public void button_action(ActionEvent event) {

        addCharacter(((Button) event.getSource()).getText());

        myParser.setAddVariable(false);
    }

    /**
     * Add one character into textob with equation
     *
     * @param character
     */
    private void addCharacter(String character) {

        String actualText = test_action.getText();

        if(actualText.equals("|")) {
            test_action.setText(character);
        }
        else {
            test_action.setText(actualText + character);
        }
    }

    /**
     * @brief count all entered values
     *
     * <p>
     *     Count all entered values. It write them into webview use KaTex engine.
     * </p>
     */
    private void count() {

        try {

            // Set sequence to default velue
            sequence = 0;

            // Get return value from parser
            myParser.clearParentVariables();
            ReturnValue returnValue = myParser.parse(test_action.getText());

            // Clear engline
            visualisation.getEngine().executeScript(
                    "telo.innerHTML = \"\";"
            );

            // While there is another value, render it
            while(returnValue != null) {

                sequence++;

                // Create element for rendering (elements are indexes with next sequence
                visualisation.getEngine().executeScript(
                        "var nadpis = document.createElement(\"H2\");" +
                                "nadpis.id = \"vyk" + String.valueOf(sequence) + "\";" +
                                "telo.appendChild(nadpis);" +
                                "var vykresleni = document.getElementById(\"vyk" + String.valueOf(sequence) + "\");"
                );

                String executeScript = "katex.render(\"" + returnValue.getTextRepresentation();

                if (returnValue.getTypeReturnValue() != TypeReturnValue.FUNCITON_DECLARATION) {
                    executeScript += "=" + String.valueOf(returnValue.getValue()) + "\", vykresleni);";
                }
                else {
                    executeScript += "\", vykresleni);";;
                }

                // Render it into element
                visualisation.getEngine().executeScript(executeScript);

                returnValue = returnValue.getNext();
            }
        }
        catch (NullPointerException ex) {
            System.err.println("Neúplný výraz: ");
        }

    }

    @FXML
    public void send_action(ActionEvent event)  {

        myParser.setAddVariable(true);
        count();

    }

    @FXML
    public void send_action_button(MouseEvent event) {

        myParser.setAddVariable(true);
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

    /**
     * Function is called when we are focused on layout and press key
     * @param keyEvent
     */
    public void layout_key_press(KeyEvent keyEvent) {

        if (keyEvent.getCode() == KeyCode.ENTER) {

            // If it is enter, set that variables can be added && count
            myParser.setAddVariable(true);
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

    /**
     * Test if the character is printable
     *
     * @param checkChar character
     *
     * @return
     */
    private boolean isPrintableCharacter(String checkChar) {

        return checkChar.matches(PRINTABLE_CHARACTER);

    }

    public void changeExpandVarsAction(ActionEvent actionEvent) {

        myParser.setExpandVariables(((CheckBox) actionEvent.getSource()).isSelected());
        count();

    }
}
