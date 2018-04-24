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
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;

/**
 * Controller for Help dialog
 *
 * @author Martin Kobelka (xkobel02@stud.fit.vutbr.cz)
 * @version 1.0
 */
public class Help {

    @FXML
    WebView webViewHelp;

    /**
     * File with "prirucka"
     */
    private final String FILE_HELP = "/app/html_templates/manual.html";

    @FXML
    public void initialize() {

        // Load page from engin
        URL path = this.getClass().getResource(FILE_HELP);
        webViewHelp.getEngine().load(path.toString());

    }

    /**
     * Action is called after click to close button
     * @param actionEvent
     */
    public void actionClose(ActionEvent actionEvent) {

        // Close actual stage
        ((Stage)((Button) actionEvent.getSource()).getScene().getWindow()).close();

    }
}
