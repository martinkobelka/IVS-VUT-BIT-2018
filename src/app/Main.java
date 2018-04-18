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

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Martin Kobelka (xkobel02@stud.fit.vutbr.cz)
 * @version 1.0
 *
 * @brief Main class of application
 *
 */
public class Main extends Application {

    /**
     * Mode of application
     */
    enum Mode{
        GRAPHIC,
        PROFFILING,
        HELP,
        ERROR
    }

    /**
     * Title of application window
     */
    private final String TITLE = "Pokročilá věděcká kalkulačka";

    /**
     * View of app
     */
    private final String APP_VIEW = "app.fxml";

    /**
     * Prefer && Minimum width of window
     */
    private final int WIDTH = 1100;

    /**
     * Prefer && Minimum height of window
     */
    private final int HEIGHT = 700;

    private static final String HELP = "Nápověda pro aplikaci";

    private static final String ERROR = "Chyba";


    @Override
    public void start(Stage primaryStage) throws Exception{

        // Load view
        Parent root = FXMLLoader.load(getClass().getResource(APP_VIEW));

        // Set some parameters of window
        primaryStage.setTitle(TITLE);
        primaryStage.setMinWidth(WIDTH);
        primaryStage.setMinHeight(HEIGHT);

        // Set && show scene
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();
    }


    /**
     * This method is called after start of program
     *
     * @param args Array of command line arguments
     */
    public static void main(String[] args) {

        // Use application mode according command line argumetns
        switch (Main.getModeFromCLI(args)) {

            case GRAPHIC:
                launch(args);
                break;

            case HELP:
                System.out.println(Main.HELP);
                break;

            case PROFFILING:
                Proffiling proffiling = new Proffiling(System.in);
                proffiling.run();
                break;

            case ERROR:
                System.err.println(Main.ERROR);
                System.exit(1);
                break;
        }

        System.exit(0);

    }

    /**
     * Get mode of this application
     *
     * @param args Array Of command line arguments
     * @return Mode
     */
    public static Mode getModeFromCLI(String[] args) {

        if(args.length == 0) {
            return Mode.GRAPHIC;
        }
        else if (args.length > 1) {
            return Mode.ERROR;
        }

        for (String arg : args) {

            if(arg.equals("-p") || arg.equals("--proffiling")) {
                return Mode.PROFFILING;
            }

            else if(arg.equals("-h") || arg.equals("--help")) {
                return Mode.HELP;
            }

        }

        return Mode.ERROR;
    }
}
