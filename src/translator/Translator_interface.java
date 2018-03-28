package translator;

import java.util.List;

public interface Translator_interface {

    /**
     * Set language. If there no language, thwor exception LanguageException. After that there
     * is default language
     *
     * @param language
     * @exception LanguageException
     */
    void setLanguage(String language) throws LanguageException;

    /**
     * Translate item from department. If the item is not in actual language,
     * it return from default language. If the item is not in default, throw LanguageException
     *
     * @param department
     * @param item
     * @exception LanguageException
     * @return Translated text
     */
    String translate(String department, String item) throws LanguageException;

    /**
     * Return list of languages
     * @return
     */
    List<String> getLanguages() throws LanguageException;

}
