package translator;

public class LanguageException extends Exception{

    public enum LanguageExceptionType{
        LANGUAGE_NOT_FOUND,
        NO_TRANSLATION,
        LANGUAGE_NOT_SET
    }

    public LanguageException(LanguageExceptionType type) {

    }

}
