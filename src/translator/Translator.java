package translator;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @brief Translator for loading and using xml languages
 *
 * <p>
 *     This class represents translator. It can load languages and
 *     geturn elements from then
 * </p>
 *
 * @author Martin Kobelka (xkobel02@stud.fit.vutbr.cz)
 * @version 1.0
 */
public class Translator implements Translator_interface {

    /**
     * Directory with languages
     */
    private final String LANGUAGES_DIRECTORY = "languages";

    /**
     * Actual language
     */
    private String language;

    /**
     * Default language
     */
    private String defaultLanguage;

    /**
     * Model of actual language
     */
    private LanguageRepresetnation languageContent;

    /**
     * Model of default language
     */
    private LanguageRepresetnation defaultLanguageContent;


    /**
     * Create new translator
     */
    public Translator() {

        // Get default language
        defaultLanguage = getDefaultLanguage();

        // When there default language, set language to default language
        if (defaultLanguage != null) {
            language = defaultLanguage; // Set actual language
            defaultLanguageContent = new LanguageRepresetnation();
            loadFromFile();
        }
    }

    /**
     * Set actual language
     *
     * @param language String actual language
     *
     * @throws LanguageException When language does not exists
     */
    public void setLanguage(String language) throws LanguageException {

        // When we select default language, it is not important to load next language
        if (language.equals(defaultLanguage)) {
            this.language = defaultLanguage;
            return;
        }

        // List of languages
        List<String> languages = getLanguages();
        boolean isSupported = false;

        // Find language in languages
        for (String actualLanguage : languages) {

            if (actualLanguage.equals(language)) {
                isSupported = true;
            }

        }

        // Test if fanguage is supported
        if (isSupported) {
            this.language = language;
        }
        else {
            throw new LanguageException(LanguageException.LanguageExceptionType.LANGUAGE_NOT_FOUND);
        }

        // Create new language content && load it from file
        languageContent = new LanguageRepresetnation();
        loadFromFile();
    }

    /**
     * Find translation of item in department
     *
     * @param department target department
     * @param item target items
     *
     * @return translation of item
     * @throws LanguageException when item does not exists
     */
    public String translate(String department, String item) throws LanguageException {

        // When there is no language, throw exception
        if (language == null && defaultLanguage == null) {
            throw new LanguageException(LanguageException.LanguageExceptionType.LANGUAGE_NOT_SET);
        }

        String result;

        if (defaultLanguage.equals(language)) {
            try {
                result = defaultLanguageContent.getItem(department, item);
            } catch (LanguageException ex) {
                throw new LanguageException(LanguageException.LanguageExceptionType.NO_TRANSLATION);
            }
            return result;
        }

        try {
            result = languageContent.getItem(department, item);
        } catch (LanguageException e) {
            try {
                result = defaultLanguageContent.getItem(department, item);
            } catch (LanguageException ex) {
                throw new LanguageException(LanguageException.LanguageExceptionType.NO_TRANSLATION);
            }
            return result;
        }
        return result;
    }

    /**
     * Get files from target directory
     * @return
     */
    private List<String> getFileNames() {

        // Cretae list for languages
        List<String> languages = new ArrayList<>();

        // Get url for these files
        URL lang_path = this.getClass().getResource(LANGUAGES_DIRECTORY);
        try {
            // Find all files && add them into list
            Files.newDirectoryStream(
                    Paths.get(lang_path.getPath()),
                    path -> path.toString().endsWith(".xml")).forEach(
                            filePath -> languages.add(
                                    filePath.toString().substring(
                                            filePath.toString().lastIndexOf('/') + 1,
                                            filePath.toString().length() - 4
                                    )
                            )
            );
        }
        catch (IOException e) {
            // Critical error, exit application
            System.err.println(e.getMessage());
            System.exit(1);
        }

        return languages;
    }

    /**
     * Get all languages from directory
     * @return List of languages
     */
    public List<String> getLanguages() {

        // List of files, which will be used for languages
        List<String> fileNames = getFileNames();
        List<String> languages = new ArrayList<>();

        // Loop over all files ad
        for (String fileName : fileNames) {

            // If it is default language
            if (fileName.startsWith("_")) {
                languages.add(fileName.substring(1));
            }
            // If it is not default language
            else {
                languages.add(fileName);
            }

        }

        // Get list of languages
        return languages;
    }

    /**
     * Load languages from file
     */
    private void loadFromFile() {

        // Create actual department, item, value && element
        String department = null, item = null, value = null, element = null;

        // Create new reader from factory
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader xsr = null;

        URL lang_path = this.getClass().getResource("./languages");

        // Create different path for default language and actual language
        String path = "";
        if(language.equals(defaultLanguage)) {
            path = lang_path.getPath() + "/_" + language + ".xml";
        }
        else{
            path = lang_path.getPath() + "/" + language + ".xml";
        }

        try {

            // Create new reader from path
            xsr = factory.createXMLStreamReader(new FileReader(path));

            // Loop over all tokens in file
            while (xsr.hasNext()) {
                if (xsr.getEventType() == XMLStreamConstants.START_ELEMENT) {
                    element = xsr.getName().getLocalPart();
                    if (element.equals("department")) {
                        department = xsr.getAttributeValue(0);
                    } else if (element.equals("item")) {
                        item = xsr.getAttributeValue(0);
                    }

                } else if (xsr.getEventType() == XMLStreamConstants.CHARACTERS) {
                    if (element.equals("item")) {
                        value = xsr.getText();
                        element = "";
                    }
                } else if ((xsr.getEventType() == XMLStreamConstants.END_ELEMENT)) {
                    if ((xsr.getName().getLocalPart().equals("item"))) {
                        if (language.equals(defaultLanguage)) {
                            defaultLanguageContent.addItem(department, item, value);
                        } else {
                            languageContent.addItem(department, item, value);
                        }
                    }
                }

                xsr.next();
            }

        }
        catch (Exception e) {

        }
        finally {

            try {
                xsr.close();
            } catch (Exception e) {
                // TODO: action for closing file exception
            }

        }
    }

    /**-
     * Find default language
     * @return
     */
    private String getDefaultLanguage() {

        // Get list of files
        List<String> filenames = getFileNames();

        // Loop over all strings
        for (String tmp : filenames) {

            // If string starts with _, it is default language
            // Remove _ and return it
            if (tmp.startsWith("_")) {
                return tmp.substring(1);
            }
        }

        // There is no default language
        return null;
    }
}
