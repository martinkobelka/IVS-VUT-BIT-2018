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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import parser.MyParser;
import setting.PernamentSetting;
import setting.SettingSingleton;
import translator.HaveTranslator;
import translator.LanguageException;
import translator.Translator;
import translator.TranslatorSingleton;

/**
 * @brief MainWindow for setting dialog
 *
 * <p>
 *     This class is controller for setting dialog
 * </p>
 *
 * @author Martin Kobelka (xkobel02@stud.dit.vutbr.cz)
 * @version 1.0
 */
public class Setting extends HaveTranslator{

    /**
     * Combobox with languages
     */
    @FXML
    public ComboBox languageView;

    public CheckBox expandFunctionsBox, expandVariablesBox;

    private PernamentSetting pernamentSetting;

    /**
     * Actual parser
     */
    MyParser parser = null;

    @FXML
    public Label languageLabel;
    public Button saveButton;

    public Setting() {
        super();
        this.pernamentSetting = SettingSingleton.getInstance();
    }

    @FXML
    public void initialize() {

        // Load actual languages to box
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll(translator.getLanguages());
        languageView.setValue(translator.getLanguage());
        languageView.setItems(list);

        // Translate all labels
        translate();

    }
    /**
     * Action reflect change language
     *
     * @param actionEvent
     */
    public void languageChangeAction(ActionEvent actionEvent) {

        try {
            translator.setLanguage((String) ((ComboBox)actionEvent.getSource()).getValue());
            pernamentSetting.language = translator.getLanguage();
            pernamentSetting.save();
        }

        catch (LanguageException e) {
            System.err.println("Language does not support !");
        }

    }

    /**
     * Translate all Strings
     */
    public void translate() {

        languageLabel.setText(translator.translate("gui", "LANGUAGE"));
        expandFunctionsBox.setText(translator.translate("gui", "EXPAND_FUNCTIONS"));
        expandVariablesBox.setText(translator.translate("gui", "EXPAND_VARIABLES"));
        saveButton.setText(translator.translate("gui", "SAVE"));

    }

    /**
     * Action is called after click to save button
     *
     * @param actionEvent
     */
    public void saveButtonClick(ActionEvent actionEvent) {

        // Close this window
        ((Stage)((Button) actionEvent.getSource()).getScene().getWindow()).close();

    }

    /**
     * Set actual parser
     *
     * @param parser
     */
    public void setParser(MyParser parser) {

        this.parser = parser;
        loadExpandValues();
    }

    /**
     * Reflect click to check box change variables
     * @param actionEvent
     */
    public void expandChangeAction(ActionEvent actionEvent) {

        parser.setExpandVariables(expandVariablesBox.isSelected());
        parser.setExpandFunctions(expandFunctionsBox.isSelected());

        pernamentSetting.expandVariables = expandVariablesBox.isSelected();
        pernamentSetting.expandFunctions = expandFunctionsBox.isSelected();

        pernamentSetting.save();

    }

    /**
     * Load actual expand values to check boxes
     */
    private void loadExpandValues() {

        expandVariablesBox.setSelected(parser.isExpandVariables());
        expandFunctionsBox.setSelected(parser.isExpandFunctions());

    }


}
