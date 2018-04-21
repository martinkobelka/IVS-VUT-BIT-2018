package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import translator.LanguageException;
import translator.Translator;
import translator.TranslatorInterface;

import java.util.List;

public class Translator_test {

    private TranslatorInterface translator_interface = null;

    @Before
    public void setUp() {
        translator_interface = new Translator();
    }

    @Test
    public void testSetLanguage() {

        try {
            translator_interface.setLanguage("english");
            translator_interface.setLanguage("czech");
        }
        catch(LanguageException ex) {
            Assert.fail("Should not have thrown any exception");
        }

    }

    @Test(expected = LanguageException.class)
    public void testSetLanguageFail() throws LanguageException {
        translator_interface.setLanguage("slovakia");
    }

    @Test
    public void testGetLanguages() {
        try {
            List<String> languages = translator_interface.getLanguages();

            Assert.assertEquals(languages.size(), 2);

            Assert.assertTrue(languages.contains(new String("czech")));
            Assert.assertTrue(languages.contains(new String("english")));

            Assert.assertFalse(languages.contains(new String("something_fuck")));
        }
        catch (LanguageException ex) {
            Assert.fail("Should not expect exception");
        }

    }

    @Test
    public void testTranslateDefaultLanguage() {

        final String FIRST_EXPECT = "Advanced science calculator";
        final String SECOND_EXPECT = "There is no Java in your computer. Please, install it in version at least 8.";
        final String THIRD_EXPECT = "Wrong count of operands";


       String first =  translator_interface.translate("gui", "title");
       String second =  translator_interface.translate("gui", "no_java");

       String third =  translator_interface.translate("math", "error_count_operands");

       Assert.assertEquals(FIRST_EXPECT, first);
       Assert.assertEquals(SECOND_EXPECT, second);

       Assert.assertEquals(THIRD_EXPECT, third);

    }

    @Test(expected = LanguageException.class)
    public void testTranslateDefaultLanguageFail() throws LanguageException {

        translator_interface.translate("gui", "titlr");

    }

    @Test
    public void testTranslateNonDefaultLanguage() {

        final String LANGUAGE = "czech";

        final String FIRST_EXPECT = "Pokročilá vědecká kalkulačka";
        final String SECOND_EXPECT = "Ve vašem počítači nebyla nalezena Java, prosím nainstalujte si ji ve verzi alespoň 8";
        final String THIRD_EXPECT = "Špatný počet operandů";

        try {
            translator_interface.setLanguage(LANGUAGE);
            String first =  translator_interface.translate("gui", "title");
            String second =  translator_interface.translate("gui", "no_java");

            String third =  translator_interface.translate("math", "error_count_operands");

            Assert.assertEquals(FIRST_EXPECT, first);
            Assert.assertEquals(SECOND_EXPECT, second);

            Assert.assertEquals(THIRD_EXPECT, third);
        }
        catch (LanguageException ex) {
            Assert.fail("Should not failed");
        }

    }

    @Test(expected = LanguageException.class)
    public void testTranslateNonDefaultLanguageFail() throws LanguageException {

        final String LANGUAGE = "czech";

        translator_interface.setLanguage(LANGUAGE);

        translator_interface.translate("gui", "titlr");

    }


}