package parser.antlr_parser;

import com.sun.javaws.exceptions.InvalidArgumentException;
import my_math.Operation;

import java.util.Arrays;

public class Transformator {


    public static Operation mapOperation(String operation) throws InvalidArgumentException {

        switch (operation) {

            case "+":
                return Operation.ADD;
            case "-":
                return Operation.SUBTRACT;
            case "*":
                return Operation.MULTIPLY;
            case "/":
                return Operation.DIVIDE;
            case "%":
                return Operation.MODULO;
            case "!":
                return Operation.FACTORIAL;
            case "cos":
                return Operation.COS;
            case "sin":
                return Operation.SIN;
            case "tag":
                return Operation.TAG;
            case "cotg":
                return Operation.COTG;
            case "sqrt":
                return Operation.SQRT;

        }

        throw new InvalidArgumentException(new String[]{"Operation"});
    }


    public static boolean isBuiltInFunction(String function) {

        String builtInFunctions[] = {"sin", "cos", "tag", "cotg", "sqrt"};

        return Arrays.asList(builtInFunctions).contains(function);

    }

}
