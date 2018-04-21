/**
 * Copyright 2018 Martin Kobelka (xkobel02@stud.fit.vutbr.cz)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package translator;

import java.util.List;

/**
 * Interface for translator (Because translator is TDD)
 *
 * @author Martin Kobelka (xkobel02@stud.fit.vutbr.cz)
 * @version 1.0
 *
 */
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
    String translate(String department, String item);

    /**
     * Return list of languages
     * @return
     */
    List<String> getLanguages() throws LanguageException;

}
