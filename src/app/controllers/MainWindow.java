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
package app.controllers;

import app.ComputingEnviroment;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import parser.antlr_parser.ReturnValue;
import parser.antlr_parser.TypeReturnValue;
import parser.symbol_table.Function;
import parser.symbol_table.Variable;
import translator.Translator;
import translator.TranslatorSingleton;

import java.io.IOException;
import java.net.URL;

/**
 * @author Martin Kobelka (xkobel02@stud.fit.vutbr.cz)
 * @version 1.0
 *
 * @brief Constroller for main app view
 */
public class MainWindow extends ComputingEnviroment {

    /**
     * Regex for printable character
     */
    private final String PRINTABLE_CHARACTER = "^[a-zA-Z0-9]|\\.|\\(|\\)|\\+|\\-|\\*|/$|=|,|\\^";

    /**
     * Address for render template
     */
    private final String RENDER_TEMPLATE = "../html_templates/eqRender.html";

    /**
     * File with template of setting dialog
     */
    private final String SETTING_TEMPLATE_FILE = "../fxml_templates/setting.fxml";

    /**
     * File with template of help dialog
     */
    private final String HELP_TEMPLATE_FILE = "../fxml_templates/help.fxml";

    /**
     * File with template of about dialog
     */
    private final String ABOUT_TEMPLATE_FILE = "../fxml_templates/about.fxml";

    /**
     * Min width of help dialog
     */
    private final double HELP_MIN_WIDTH = 800;

    /**
     * Min height of help dialog
     */
    private final double HELP_MIN_HEIGHT = 800;

    /**
     * Translator for translating texts
     */
    private Translator translator = null;

    /**
     * Owner of this controller
     */
    private Window TARGET_WINDOW = null;

    @FXML
    public Button button_null;
    @FXML
    public TextField test_action;
    @FXML
    public WebView visualisation;

    @FXML
    public Label settingLabel, variablesLabel, usersLabel, functionsLabel, helpLabel, aboutLabel;

    /**
     * List View for variables
     */
    @FXML
    public ListView<Variable> symbolVariableListView;

    /**
     * List View for functions
     */
    @FXML
    public ListView<Function> symbolFunctionsListView;

    /**
     * Counter of equation which was written
     */
    private int sequence;

    @FXML
    public void initialize() {

        // Get translator
        translator = TranslatorSingleton.getTranslator();

        // Get address of render html file
        URL url = this.getClass().getResource(RENDER_TEMPLATE);

        // Load visualisation page
        visualisation.getEngine().load(url.toString());

        // Create lists for tables
        ObservableList<Variable> listOfVariables = tableOfVariables.getVariables();
        ObservableList<Function> listOfFunctions = tableOfFunctions.getFunctions();

        // Set ovservalble lists to views
        symbolVariableListView.setItems(listOfVariables);
        symbolFunctionsListView.setItems(listOfFunctions);

        sequence = 0;

        // Add listener for changing value in text box
        test_action.textProperty().addListener((observable, oldValue, newValue) -> {
            myParser.setAddVariable(false);
            count();
        });

        // Translate
        translate();
    }

    /**
     * Translate all texts in forms
     */
    public void translate() {

        // Translate just when translator is not null
        if(translator != null) {

            settingLabel.setText(translator.translate("gui", "SETTING"));
            usersLabel.setText(translator.translate("gui", "USERS"));
            variablesLabel.setText(translator.translate("gui", "VARIABLES"));
            functionsLabel.setText(translator.translate("gui", "FUNCTIONS"));
            helpLabel.setText(translator.translate("gui", "HELP"));
            usersLabel.setText(translator.translate("gui", "USERS"));
            aboutLabel.setText(translator.translate("gui", "ABOUT"));


        }
    }


    /**
     * Action which add character into text box
     *
     * @param event
     */
    @FXML
    public void addCharacterAction(ActionEvent event) {

        // Get character from button
        addCharacter(((Button) event.getSource()).getText());

        // Add character into text view
        myParser.setAddVariable(false);
    }

    /**
     * Add one character into textob with equation
     *
     * @param character
     */
    private void addCharacter(String character) {

        // Get actual text
        String actualText = test_action.getText();

        // Remve first character || not
        if(actualText.equals("|")) {
            test_action.setText(character);
        }
        else {
            test_action.setText(actualText + character);
        }
    }

    /**
     * count all entered values
     *
     * <p>
     *
     * Count all entered values. It write them into webview use KaTex engine.
     *
     */
    private void count() {


        // Initialize counter for equations
        sequence = 0;

        try {

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

    /**
     * Action is called after click to button
     * @param event
     */
    @FXML
    public void countWithEnterAction(ActionEvent event)  {
        countWithAddVariable();
    }

    @FXML
    public void countWithEnterAction(MouseEvent event)  {
        countWithAddVariable();
    }

    /**
     * Action is called after click to text field
     * @param event
     */
    @FXML
    public void textFieldClick(MouseEvent event) {

        // If there is | symbol,, remove it
        if(test_action.getText().equals("|")) {
            test_action.setText("");
        }
    }


    /**
     * Action which is called after backspace slick
     * @param mouseEvent
     */
    public void backspaceClick(MouseEvent mouseEvent) {
        removeChar();
    }

    /**
     * Remove one character from text field
     */
    private void removeChar() {

        // Get actual text && count length
        String actualText = test_action.getText();
        int length = actualText.length();

        // Remove char when length is bigger than 0
        if(length > 0) {
            test_action.setText(actualText.substring(0, length - 1));
        }
    }

    /**
     * Function is called when we are focused on layout and press key
     *
     * @param keyEvent
     */
    public void layoutKeyPress(KeyEvent keyEvent) {

        // After exter, count && add variables
        if (keyEvent.getCode() == KeyCode.ENTER) {
            countWithAddVariable();
        }

        else {

            if (!test_action.isFocused()) {

                // After backspace, remove char
                if (keyEvent.getCode() == KeyCode.BACK_SPACE) {
                    removeChar();
                }

                else {

                    // If it is printable character, add it
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

        // Check character with regular expression
        return checkChar.matches(PRINTABLE_CHARACTER);

    }

    /**
     * Action is called after click to setting box
     *
     * @param mouseEvent
     */
    public void settingClick(MouseEvent mouseEvent) {

        getOwner();

        // Create new stage &&
        Stage dialog = new Stage();

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(SETTING_TEMPLATE_FILE));

        try {

            AnchorPane pane = loader.load();

            // Get Controller
            Setting controller = loader.getController();
            controller.setParser(myParser);

            Scene scene = new Scene(pane);
            dialog.initModality(Modality.WINDOW_MODAL);
            dialog.initOwner(TARGET_WINDOW);
            dialog.setTitle(translator.translate("gui", "SETTING"));
            dialog.setScene(scene);

            dialog.showAndWait();

        }
        catch(IOException ex) {
            System.err.println("Error in creating dialog");
        }

    }

    /**
     * This method is calleed after click to help box
     *
     * @param mouseEvent
     */
    public void helpBoxClick(MouseEvent mouseEvent) {

        // Get owner window
        getOwner();

        // Create stage && load view file
        Stage helpDialog = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(HELP_TEMPLATE_FILE));

        try {
            VBox vBox = loader.load();

            Scene scene = new Scene(vBox);

            helpDialog.initModality(Modality.WINDOW_MODAL);
            helpDialog.initOwner(TARGET_WINDOW);
            helpDialog.setTitle(translator.translate("gui", "HELP"));
            helpDialog.setMinWidth(HELP_MIN_WIDTH);
            helpDialog.setMinHeight(HELP_MIN_HEIGHT);
            helpDialog.setScene(scene);

            helpDialog.showAndWait();

        }
        catch(IOException ex) {
            // TODO: Produce error
        }
    }

    /**
     * Count all equations, variables && functions should be added
     */
    private void countWithAddVariable() {
        myParser.setAddVariable(true);
        count();
    }

    /**
     * Get owner of this controller
     */
    private void getOwner() {

        // Get owner of this controller
        TARGET_WINDOW = button_null.getScene().getWindow();

    }

    /**
     * Action, which is invocate after click to abot box
     *
     * @param mouseEvent
     */
    public void aboutBoxClick(MouseEvent mouseEvent) {

        /**
         * Width of dialog
         */
        final double WIDTH = 520;
        /**
         * height of dialog
         */
        final double HEIGHT = 400;

        // Get parent dialog stage
        getOwner();

        Stage aboutDialog = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource(ABOUT_TEMPLATE_FILE));

        try {
            VBox vBox = loader.load();
            Scene scene = new Scene(vBox);

            aboutDialog.setScene(scene);
            aboutDialog.setMinHeight(HEIGHT);
            aboutDialog.setMaxHeight(HEIGHT);

            aboutDialog.setMinWidth(WIDTH);
            aboutDialog.setMaxWidth(WIDTH);

            aboutDialog.initOwner(TARGET_WINDOW);
            aboutDialog.initModality(Modality.WINDOW_MODAL);
            aboutDialog.setTitle(translator.translate("gui", "ABOUT"));

            aboutDialog.showAndWait();
        }
        catch (IOException ex) {
            // TODO: Produce error
        }

    }
}
