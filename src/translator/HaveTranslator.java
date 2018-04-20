package translator;

public class HaveTranslator {


    /**
     * Translator for translate Strings
     */
    protected Translator translator = null;

    /**
     * Create new error enviroment
     */
    public HaveTranslator() {
        translator = TranslatorSingleton.getTranslator();
    }

}
