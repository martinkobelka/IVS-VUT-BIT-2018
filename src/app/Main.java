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

import translator.Translator;
import translator.TranslatorSingleton;

/**
 * @author Martin Kobelka (xkobel02@stud.fit.vutbr.cz)
 * @version 1.0
 *
 * @brief Main class of application
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
        ERROR
    }

    /**
     * This method is called after start of program
     *
     * @param args Array of command line arguments
     */
    public static void main(String[] args) {

        // Get translator from singleton
        Translator translator = TranslatorSingleton.getTranslator();

        // Use application mode according command line argumetns
        switch (Main.getModeFromCLI(args)) {

            case GRAPHIC:

                App app = new App();
                app.run(args);

                break;

            case HELP:
                System.out.println(translator.translate("cli", "HELP"));
                break;

            case PROFFILING:
                Proffiling proffiling = new Proffiling(System.in);
                proffiling.run();
                break;

            case ERROR:
                System.err.println(translator.translate("cli", "ERROR"));
                System.exit(1);
                break;
        }

        // Exit with OK status
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
