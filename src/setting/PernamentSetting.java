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
package setting;

import translator.HaveTranslator;
import java.io.*;


/**
 * Loading && saving permanent setting
 *
 * @author Martin Kobelka (xkobel02@stud.fit.vutbr.cz)
 * @version 1.0
 *
 */
public class PernamentSetting extends HaveTranslator{

    /**
     * Name of app
     */
    private final String APP_NAME = "my007calculator";

    /**
     * Actual operating system
     */
    private String OS = System.getProperty("os.name").toLowerCase();

    /**
     * Actual language
     */
    public String language = translator.getDefaultLanguage();

    /**
     * Flag for expand variables
     */
    public boolean expandVariables = true;

    /**
     * Flag for expand functions
     */
    public boolean expandFunctions = true;

    /**
     * File path
     */
    private String filePath = null;

    /**
     * Create new permanent setting
     */
    public PernamentSetting() {
        filePath = getFile();
        load();
    }

    /**
     * Load data from file
     */
    public void load() {

        int counter = 0;

        try {

            BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));

            String actualLine = "";
            while((actualLine = reader.readLine()) != null) {
                counter++;
                loadSetting(actualLine, counter);

            }

        }
        catch(IOException ex) {
            System.err.println("Error in loading file :(");
        }

    }

    /**
     * Save data into file
     */
    public void save() {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filePath)));

            writer.write(language);
            writer.newLine();
            writer.write(String.valueOf(expandVariables));
            writer.newLine();
            writer.write(String.valueOf(expandFunctions));
            writer.newLine();
            writer.flush();
            writer.close();
        }
        catch (IOException ex) {
            System.err.println("Error in writing to file");
        }

    }

    /**
     * Load one setting
     *
     * @param actualLine actual line in file
     * @param counter kex
     */
    private void loadSetting(String actualLine, int counter) {

        // Load according counter
        switch (counter) {

            // Load language
            case 1:
                language = actualLine;
                break;

             // Load expand variables
            case 2:
                expandVariables = Boolean.valueOf(actualLine);
                break;

            // Load expand functions
            case 3:
                expandFunctions = Boolean.valueOf(actualLine);
                break;
        }

    }

    /**
     * Get path for saving file
     * @return
     */
    private String getFile() {

        if (isWindows()) {

            String path = System.getenv("APPDATA") + File.separator + "my007calculator";
            return createOrLoadFile(path);

        }

        else if (isUnix()) {

            String path = System.getProperty( "user.home") + File.separator + APP_NAME;
            return createOrLoadFile(path);

        }

        System.err.println("Unsupported operationg system");

        return null;

    }

    /**
     * Create || load file && return path
     * @param path
     * @return
     */
    private String createOrLoadFile(String path) {

        File fCesta = new File(path);

        if(!fCesta.isDirectory()) {

            if(!fCesta.mkdirs()) {
                System.err.println("Error in creating directory");
            }

        }

        File fData = new File(path + File.separator + "data.txt");

        if(fData.exists()) {
            return fData.getAbsolutePath();
        }
        else {

            try {

                if(!fData.createNewFile()) {
                    System.err.println("Chyba při vytváření konfiguračního souboru");
                }
                else {
                    filePath = fData.getAbsolutePath();
                    save();
                    return fData.getAbsolutePath();
                }
            }

            catch (IOException e) {
                System.err.println("Chyba při vytváření konfiguračního souboru");
            }
        }

        return null;

    }

    /**
     * Is windows?
     *
     * @return
     */
    private boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }

    /**
     * Is unix
     * @return
     */
    private boolean isUnix() {
        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
    }

    /**
     * Translate all translatable strings in object
     */
    @Override
    public void translate() {

    }
}
