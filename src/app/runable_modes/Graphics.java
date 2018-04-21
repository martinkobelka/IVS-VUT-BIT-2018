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
package app.runable_modes;

import app.RunableMode;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import translator.Translator;
import translator.TranslatorSingleton;

/**
 * Enviroment mode for graphics mode
 *
 * @author Martin Kobelka (xkobel02@stud.fit.vutbr.cz)
 * @version 1.0
 */
public class Graphics extends Application implements RunableMode {

    /**
     * View of app
     */
    private final String APP_VIEW = "../fxml_templates/app.fxml";

    /**
     * Path of icon
     */
    private final String ICON_PATH = "../icones/icon.png";

    /**
     * Prefer && Minimum width of window
     */
    private final int WIDTH = 1100;

    /**
     * Prefer && Minimum height of window
     */
    private final int HEIGHT = 700;

    /**
     * Translator for translationg texts
     */
    Translator translator = null;

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Get tranlator from singleton
        translator = TranslatorSingleton.getTranslator();

        // Load view
        Parent root = FXMLLoader.load(getClass().getResource(APP_VIEW));

        // Set some parameters of window
        primaryStage.setTitle(translator.translate("gui", "TITLE"));
        primaryStage.setMinWidth(WIDTH);
        primaryStage.getIcons().add(new Image(getClass().getResource(ICON_PATH).toString()));
        primaryStage.setMinHeight(HEIGHT);

        // Set && show scene
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();

    }

    public void run(String[] args) {
        launch(args);
    }

}
