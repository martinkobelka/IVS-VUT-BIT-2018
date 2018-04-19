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
package parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import parser.antlr_parser.CalculatorBaseVisitor;
import parser.antlr_parser.CalculatorLexer;
import parser.antlr_parser.CalculatorParser;
import parser.antlr_parser.ReturnValue;
import parser.symbol_table.TableOfFunctions;
import parser.symbol_table.TableOfVariables;
import parser.symbol_table.Variable;

import java.text.ParseException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author Martin Kobelka (xkobel02@stud.fit.vutbr.cz)
 * @version 1.0
 *
 * @brief Class for parsing input text from textView
 */
public class MyParser{

    /**
     * Local table of variables
     */
    TableOfVariables tableOfVariables;

    /**
     * Local table of functions
     */
    TableOfFunctions tableOfFunctions;
    /**
     * Flag which should tell parser if can variables can be added into table of variables
     */
    private boolean addVariable;
    /**
     * Flag, which sould tell parser if variables can be expand
     */
    private boolean expandVariables;

    /**
     * Set of names of parent variables
     */
    private Set<String> parentVariables;

    /**
     * Create new Parser
     *
     * @param tableOfVariables
     * @param tableOfFunctions
     */
    public MyParser(TableOfVariables tableOfVariables, TableOfFunctions tableOfFunctions) {

        this.tableOfVariables = tableOfVariables;
        this.tableOfFunctions = tableOfFunctions;
        this.addVariable = true;
        this.parentVariables = new HashSet<>();
    }

    /**
     * Setter for {@code value}
     *
     * @param value
     */
    public void setAddVariable(boolean value) {
        addVariable = value;
    }

    /**
     * Getter for {@code expandVariables}
     * @return
     */
    public boolean isExpandVariables() {
        return expandVariables;
    }

    /**
     * Setter for {@code expandVariables}
     *
     * @param expandVariables
     */
    public void setExpandVariables(boolean expandVariables) {
        this.expandVariables = expandVariables;
    }

    /**
     * Getter for {@code addVariable}
     *
     * @return
     */
    public boolean isAddVariable() {
        return addVariable;
    }

    /**
     * Set list of parent variables
     * @param parentVariables
     */
    public void setParentVariables(Set<String> parentVariables) {
        this.parentVariables = parentVariables;
    }

    public void clearParentVariables() {
        parentVariables = new HashSet<>();
    }

    /**
     * Parse && count input and return value
     *
     * @param input Input for parsing
     *
     * @return Value with
     */
    public ReturnValue parse(String input){

        // Get char stream from string
        CharStream stream = new ANTLRInputStream(input);

        // Use lexical analysis
        CalculatorLexer lexer = new CalculatorLexer(stream);
        CommonTokenStream token_stream = new CommonTokenStream(lexer);

        // Use syntactic analysis
        CalculatorParser parser = new CalculatorParser(token_stream);
        ParseTree tree = parser.prog();

        // Visit it with visitor
        CalculatorBaseVisitor baseVisitor = new CalculatorBaseVisitor(tableOfVariables, tableOfFunctions);
        baseVisitor.setAddVariable(addVariable);
        baseVisitor.setExpandVariables(expandVariables);
        baseVisitor.setParentVariables(parentVariables);

        // Return value from visitor
        return baseVisitor.visit(tree);

    }
}
