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
package app.runable_modes;

import app.RunableMode;
import translator.HaveTranslator;

/**
 *
 * Enviroment for Error cli
 *
 * @author Martin Kobelka (xkobel02@stud.fit.vutbr.cz)
 * @version 1.0
 *
 */
public class Error extends HaveTranslator implements RunableMode {

    /**
     * Run application
     *
     * @param args
     */
    @Override
    public void run(String[] args) {

        // Write error report && exit with 1

        System.err.println(translator.translate("cli", "ERROR_ARGUMENTS"));
        System.exit(1);

    }
}
