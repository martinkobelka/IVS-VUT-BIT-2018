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

public class MyParser{

    TableOfVariables tableOfVariables;
    TableOfFunctions tableOfFunctions;
    private boolean addVariable;

    public MyParser(TableOfVariables tableOfVariables, TableOfFunctions tableOfFunctions) {

        this.tableOfVariables = tableOfVariables;
        this.tableOfFunctions = tableOfFunctions;
        this.addVariable = true;
    }

    public void sedAddVariable(boolean value) {
        addVariable = value;
    }

    public ReturnValue parse(String input){

        CharStream stream = new ANTLRInputStream(input);

        CalculatorLexer lexer = new CalculatorLexer(stream);

        CommonTokenStream token_stream = new CommonTokenStream(lexer);

        CalculatorParser parser = new CalculatorParser(token_stream);

        ParseTree tree = parser.prog();

        CalculatorBaseVisitor baseVisitor = new CalculatorBaseVisitor(tableOfVariables, tableOfFunctions);
        baseVisitor.setAddVariable(addVariable);

        ReturnValue value = baseVisitor.visit(tree);

        return value;

    }

    public static void main(String args[]) {

        TableOfVariables tableOfVariables = new TableOfVariables();
        TableOfFunctions tableOfFunctions = new TableOfFunctions();

        MyParser parser = new MyParser(tableOfVariables, tableOfFunctions);
        parser.parse("-1,2,3");

        TableOfVariables table = new TableOfVariables();

        Variable variable = new Variable("e", "blabla");

        if(table.isVariableExists(variable)) {
            System.out.println("variable existuje");
        }
        else {
            System.out.println("Variable neexistuje");
        }

    }


}
