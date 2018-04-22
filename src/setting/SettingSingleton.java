package setting;

public class SettingSingleton {

    private static PernamentSetting ourInstance = null;

    public static PernamentSetting getInstance() {

        if(ourInstance == null) {
            ourInstance = new PernamentSetting();
            ourInstance.load();
        }

        return ourInstance;
    }

    private SettingSingleton() {
    }
}
