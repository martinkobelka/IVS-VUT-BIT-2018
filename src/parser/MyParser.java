package parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import parser.antlr_parser.CalculatorBaseVisitor;
import parser.antlr_parser.CalculatorLexer;
import parser.antlr_parser.CalculatorParser;
import parser.antlr_parser.ReturnValue;

public class MyParser{

    public static void main(String args[]) {

        MyParser parser = new MyParser();
        parser.parse("10-10*10/10");
    }

    public ReturnValue parse(String input){

        CharStream stream = new ANTLRInputStream(input);

        CalculatorLexer lexer = new CalculatorLexer(stream);

        CommonTokenStream token_stream = new CommonTokenStream(lexer);

        CalculatorParser parser = new CalculatorParser(token_stream);

        ParseTree tree = parser.prog();


       return new CalculatorBaseVisitor().visit(tree);



    }

}
