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

import app.runable_modes.InternalError;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * Main class of application
 *
 * @author Martin Kobelka (xkobel02@stud.fit.vutbr.cz)
 * @version 1.0
 *
 */
public class Main{

    /**
     * Mode of application
     */
    enum Mode{
        GRAPHIC,
        PROFFILING,
        HELP,
        ERROR,
        CLI
    }

    /**
     * Package with runable modes
     */
    private final String PACKAGE_RUNABLE_MODES = "app.runable_modes";

    /**
     * This method is called after start of program
     *
     * @param args Array of command line arguments
     */
    public static void main(String[] args) {

        Main main = new Main();

        // Get object with runable model
        RunableMode runableMode = main.getRunableInstance(args);

        // Run application with it model
        runableMode.run(args);

        // Exit with OK status
        System.exit(0);

    }

    /**
     * Get instance of class, which should be runned
     * @param args
     * @return
     */
    public RunableMode getRunableInstance(String[] args) {

        try {
            // Find  this class, && return its instance
            Class<?> c = Class.forName(PACKAGE_RUNABLE_MODES + "." + mapModeToClass(getModeFromCLI(args)));
            Constructor<?> cons = c.getConstructor();
            return (RunableMode) cons.newInstance();
        }

        // If we cannot find this class, return error class
        catch (ClassNotFoundException | NoSuchMethodException |
                InstantiationException | IllegalAccessException | InvocationTargetException e) {

            return new InternalError();

        }
    }

    /**
     * Get mode of this application
     *
     * @param args Array Of command line arguments
     * @return Mode
     */
    private Mode getModeFromCLI(String[] args) {

        // Test count of args
        if(args.length == 0) {
            return Mode.GRAPHIC;
        }

        // Support just one argument
        else if (args.length > 1) {
            return Mode.ERROR;
        }

        // Loop over all command line arguments
        for (String arg : args) {

            // Test actual argument
            switch (arg) {

                case "-p":
                case "--proffiling":
                    return Mode.PROFFILING;

                case "-h":
                case "--help":
                    return Mode.HELP;

                case "-c":
                case "--cli":
                    return Mode.CLI;
            }
        }

        // Nothing found, return error mode
        return Mode.ERROR;
    }

    /**
     * Map mode to its string representation
     *
     * @param mode
     * @return
     */
    private String mapModeToClass(Mode mode) {

        switch (mode) {
            case CLI:
                return "Cli";
            case ERROR:
                return "Error";
            case HELP:
                return "Help";
            case GRAPHIC:
                return "Graphics";
            case PROFFILING:
                return "Proffiling";
        }

        return "Error";

    }
}
