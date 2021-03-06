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
 * Singleton for getting translator instance from everywhere
 *
 * @author Martin Kobelka (xkobel02@stud.fit.vutbr.cz)
 * @version 1.0
 *
 */
public class TranslatorSingleton {

    /**
     * Instance of translator
     */
    private static Translator translator = null;

    /**
     * Private (protected) constructor
     */
    protected TranslatorSingleton() {

    }

    /**
     * Get instance of translator
     *
     * @return Instance of translator
     */
    public static Translator getTranslator() {

        // If instance does not exists, create it
        if(translator == null) {
            translator = new Translator();
        }

        return translator;
    }
}
