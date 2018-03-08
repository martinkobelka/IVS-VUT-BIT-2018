package translator;

public class LanguageException extends Exception{

    public enum LanguageExceptionType{
        LANGUAGE_NOT_FOUND,
        NO_TRANSLATION
    }

    public LanguageException(LanguageExceptionType type) {

    }

}
