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

/**
 *
 * Exception, which is used in languages
 *
 * @author Martin Kobelka (xkobel02@stud.fit.vutbr.cz)
 * @version 1.0
 *
 */
public class LanguageException extends Exception{

    /**
     * Type of language exception
     */
    public enum LanguageExceptionType{
        LANGUAGE_NOT_FOUND,
        NO_TRANSLATION,
        LANGUAGE_NOT_SET
    }

    /**
     * Actual type of language exception
     */
    private LanguageExceptionType languageExceptionType;


    /**
     * Create new language exception
     *
     * @param type type of exception
     */
    public LanguageException(LanguageExceptionType type) {
        languageExceptionType = type;

    }

    /**
     * Get type of exception
     *
     * @return type of exception
     */
    public LanguageExceptionType getLanguageExceptionType() {
        return languageExceptionType;
    }


}
