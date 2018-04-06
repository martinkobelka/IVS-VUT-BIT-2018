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

public class Translator implements Translator_interface {

    private String language;
    private String default_language;
    private LanguageRepresetnation language_content;
    private LanguageRepresetnation default_language_content;

    public Translator() {
        default_language = getDefaultLanguage();
        if (default_language != null) {
            language = default_language;
            default_language_content = new LanguageRepresetnation();
            loadFromFile();
        }
    }

    public void setLanguage(String language) throws LanguageException {

        if (language.equals(default_language.substring(1))) {
            this.language = default_language;
            return;
        }

        List<String> languages = getLanguages();
        boolean is_supported = false;

        for (String tmp : languages) {
            if (language.equals(tmp)) {
                is_supported = true;
            }
        }
        if (is_supported) {
            this.language = language;
        } else {
            throw new LanguageException(LanguageException.LanguageExceptionType.LANGUAGE_NOT_FOUND);
        }

        language_content = new LanguageRepresetnation();
        loadFromFile();
    }

    public String translate(String department, String item) throws LanguageException {
        if (language == null && default_language == null) {
            throw new LanguageException(LanguageException.LanguageExceptionType.LANGUAGE_NOT_SET);
        }

        String result;

        if (default_language.equals(language)) {
            try {
                result = default_language_content.getItem(department, item);
            } catch (LanguageException ex) {
                throw new LanguageException(LanguageException.LanguageExceptionType.NO_TRANSLATION);
            }
            return result;
        }

        try {
            result = language_content.getItem(department, item);
        } catch (LanguageException e) {
            try {
                result = default_language_content.getItem(department, item);
            } catch (LanguageException ex) {
                throw new LanguageException(LanguageException.LanguageExceptionType.NO_TRANSLATION);
            }
            return result;
        }
        return result;
    }

    private List<String> getFileNames() {
        List<String> languages = new ArrayList<>();
        URL lang_path = this.getClass().getResource("./languages");
        try {
            Files.newDirectoryStream(Paths.get(lang_path.getPath()), path -> path.toString().endsWith(".xml")).forEach(filePath -> languages.add(filePath.toString().substring(filePath.toString().lastIndexOf('/') + 1, filePath.toString().length() - 4)));
        } catch (IOException e) {
        }

        return languages;
    }

    public List<String> getLanguages() {
        List<String> fileNames = getFileNames();
        List<String> languages = new ArrayList<>();

        for (String fileName : fileNames) {
            if (fileName.startsWith("_")) {
                String tmp = fileName.substring(1);
                languages.add(tmp);
            } else {
                languages.add(fileName);
            }
        }
        return languages;
    }

    private void loadFromFile() {
        String department = null, item = null, value = null, element = null;
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader xsr = null;

        URL lang_path = this.getClass().getResource("./languages");
        String path = lang_path.getPath() + "/" + language + ".xml";

        try {
            xsr = factory.createXMLStreamReader(new FileReader(path));
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
                        if (language.equals(default_language)) {
                            default_language_content.addItem(department, item, value);
                        } else {
                            language_content.addItem(department, item, value);
                        }
                    }
                }

                xsr.next();
            }

        } catch (Exception e) {

        } finally {
            try {
                xsr.close();
            } catch (Exception e) {
            }
        }
    }

    private String getDefaultLanguage() {
        List<String> filenames = getFileNames();
        for (String tmp : filenames) {
            if (tmp.startsWith("_")) {
                return tmp;
            }
        }
        return null;
    }
}
