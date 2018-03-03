package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    /**
     * Title of application window
     */
    private static final String TITLE = "Pokročilá věděcká kalkulačka";

    private static final int WIDTH = 880;
    private static final int HEIGHT = 700;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("app.fxml"));
        primaryStage.setTitle(Main.TITLE);
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.setMinWidth(WIDTH);
        primaryStage.setMinHeight(HEIGHT);
        primaryStage.show();
    }

    /**
     * This method is called after start of program
     *
     * @param args Arguments of command line
     */
    public static void main(String[] args) {
        launch(args);
    }
}
