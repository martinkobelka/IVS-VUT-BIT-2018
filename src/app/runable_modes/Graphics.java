package app.runable_modes;

import app.RunableMode;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import translator.Translator;
import translator.TranslatorSingleton;

public class Graphics extends Application implements RunableMode {

    /**
     * View of app
     */
    private final String APP_VIEW = "../fxml_templates/app.fxml";

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
        primaryStage.setMinHeight(HEIGHT);

        // Set && show scene
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();

    }

    public void run(String[] args) {
        launch(args);
    }

}
