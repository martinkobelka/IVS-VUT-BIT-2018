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
package app;

import parser.MyParser;
import parser.symbol_table.TableOfFunctions;
import parser.symbol_table.TableOfVariables;
import translator.HaveTranslator;

/**
 * Computing enviroment. Every mode in calculator extends from it
 *
 * @author Martin Kobelka (xkobel02@stud.fit.vutbr.cz)
 * @version 1.0
 *
 */
public class ComputingEnviroment extends HaveTranslator{

    /**
     * Run representation of table of variables
     */
    protected TableOfVariables tableOfVariables;

    /**
     * Run representation of table of functions
     */
    protected TableOfFunctions tableOfFunctions;

    /**
     * Parser for parsing expression
     */
    protected MyParser myParser;

    /**
     * Create new Compuring enviroment
     */
    public ComputingEnviroment() {

        super();

        // Create tables
        tableOfVariables = new TableOfVariables();
        tableOfFunctions = new TableOfFunctions();

        // Create parser
        myParser = new MyParser(tableOfVariables, tableOfFunctions);
    }

    @Override
    public void translate() {

    }
}
