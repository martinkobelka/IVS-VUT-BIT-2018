package translator;

public class TranslatorSingleton {

    private static Translator translator = null;

    protected TranslatorSingleton() {

    }

    public static Translator getTranslator() {

        if(translator == null) {
            translator = new Translator();
        }

        return translator;
    }
}
