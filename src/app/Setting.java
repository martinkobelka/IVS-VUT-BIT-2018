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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
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
public class Setting {

    /**
     * Combobox with languages
     */
    @FXML
    public ComboBox languageView;

    /**
     * Actual translator
     */
    private Translator translator;

    @FXML
    public void initialize() {

        /**
         * Get translator from singleton
         */
        translator = TranslatorSingleton.getTranslator();

        // Load actual languages to box
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll(translator.getLanguages());
        languageView.setValue(translator.getDefaultLanguage());
        languageView.setItems(list);


    }
}
