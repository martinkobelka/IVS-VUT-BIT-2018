package translator;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Translator implements Translator_interface {

    private String language;

    @Override
    public void setLanguage(String language) throws LanguageException {
        List<String> languages;
        languages = getLanguages();
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
    }

    @Override
    public String translate(String department, String item) throws LanguageException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader xsr = null;

        if (language == null) {
            throw new LanguageException(LanguageException.LanguageExceptionType.LANGUAGE_NOT_SET);
        }

        String path = "./languages/" + language + ".xml";
        String element = "";
        String tmp = "";
        boolean depfound = false;

        try {
            xsr = factory.createXMLStreamReader(new FileReader(path));
            //-------------------------------------------------------------//
            while (xsr.hasNext()) {
                if (xsr.getEventType() == XMLStreamConstants.START_ELEMENT) {
                    element = xsr.getName().getLocalPart();
                    if (element.equals("department")) {
                        tmp = xsr.getAttributeValue(0);
                        if (tmp.equals(department)) {
                            depfound = true;
                        }
                    }

                } else if (depfound && xsr.getEventType() == XMLStreamConstants.CHARACTERS) {
                    if (element.equals("item")) {
                        if (xsr.getAttributeValue(0).equals(item)) {
                            return xsr.getText();
                        }
                        element = "";
                    }
                }
                xsr.next();
            }
            setLanguage("_english");
            translate(department, item);
            throw new LanguageException(LanguageException.LanguageExceptionType.NO_TRANSLATION);

        } catch (Exception e) {

        } finally {
            try {
                xsr.close();
            } catch (Exception e) {
            }
        }
        return null;
    }

    public List<String> getLanguages() throws LanguageException {
        List<String> languages = new ArrayList<>();
        Files.newDirectoryStream(Paths.get("./languages"),
                path -> path.toString().endsWith(".xml")).forEach(filePath -> languages.add(filePath.toString()));

        for (String tmp : languages) {
            tmp = tmp.substring(tmp.lastIndexOf('/') + 1, tmp.length() - 4);
        }
        return languages;
    }
}
