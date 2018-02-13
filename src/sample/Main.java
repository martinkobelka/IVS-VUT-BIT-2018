package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    /**
     * Title of applcation windows
     */
    private static final String TITLE = "Moje super kalkulačka";

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle(Main.TITLE);
        primaryStage.setScene(new Scene(root, 300, 275));
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
