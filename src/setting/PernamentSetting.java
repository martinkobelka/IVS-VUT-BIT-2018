package setting;

import translator.HaveTranslator;

import java.io.*;

public class PernamentSetting extends HaveTranslator{

    private final String APP_NAME = "my007calculator";

    private String OS = System.getProperty("os.name").toLowerCase();

    public String language = translator.getDefaultLanguage();
    public boolean expandVariables = true;
    public boolean expandFunctions = true;

    private String filePath;

    public PernamentSetting() {
        filePath = getFile();
        load();
    }

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

    private boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }

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
