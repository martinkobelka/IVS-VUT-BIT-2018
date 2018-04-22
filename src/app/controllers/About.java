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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import translator.HaveTranslator;

/**
 * Constroller for about dialog
 *
 * @author Martin Kobelka (xkobel02@stud.fit.vutbr.cz)
 * @version 1.0
 *
 */
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
