package setting;

public class PernamentSetting {

    private static String OS = System.getProperty("os.name").toLowerCase();

    public String language;
    public boolean expandVariables;
    public boolean expandFunctions;

    private String filePath;

    public PernamentSetting() {
        getFile();
    }

    private void getFile() {



    }


    public void load() {



    }

    public void save() {

    }

}
