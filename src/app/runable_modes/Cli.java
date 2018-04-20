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

import app.ComputingEnviroment;
import app.RunableMode;
import parser.antlr_parser.ReturnValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Cli mode
 *
 * @author Martin Kobelka (xkobel02@stud.fit.vutbr.cz)
 * @version 1.0
 */
public class Cli extends ComputingEnviroment implements RunableMode {

    /**
     * Source input stream for application
     */
    InputStream inputStream;

    /**
     * Create new CLI enviroment for calculator
     *
     * @param inputStream
     */
    public Cli(InputStream inputStream) {
        super();
        this.inputStream = inputStream;
    }


    /**
     * Run command line mode
     */
    public void run(String[] args) {

        // Create buffered reader
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {

            // Get on line in loop
            String line;
            while ((line = reader.readLine()) != null) {

                // Parse values
                ReturnValue returnValue = myParser.parse(line);

                if(returnValue != null) {
                    // Print output
                    System.out.println(line + " = " + returnValue.getValue());
                }

            }

        }

        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

    }
}
