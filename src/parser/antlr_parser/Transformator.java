package parser.antlr_parser;

import my_math.Operation;

import java.util.Arrays;

public class Transformator {

    public static int getCountOfOperands(Operation operation) {
        if(operation != Operation.NSQRT) {
            return 1;
        }
        else {
            return 2;
        }
    }


    public static Operation mapOperation(String operation){

        switch (operation) {

            case "+":
                return Operation.ADD;
            case "-":
                return Operation.SUBTRACT;
            case "*":
                return Operation.MULTIPLY;
            case "/":
                return Operation.DIVIDE;
            case "^":
                return Operation.POWER;
            case "%":
                return Operation.MODULO;
            case "!":
                return Operation.FACTORIAL;
            case "cos":
                return Operation.COS;
            case "sin":
                return Operation.SIN;
            case "tan":
                return Operation.TAG;
            case "cotg":
                return Operation.COTG;
            case "sqrt":
                return Operation.SQRT;
            case "ln":
                return Operation.LN;
            case "log":
                return Operation.LOG;
            case "nsqrt":
                return Operation.NSQRT;

        }

        return null;
    }


    public static boolean isBuiltInFunction(String function) {

        String builtInFunctions[] = {"sin", "cos", "tan", "cotg", "sqrt", "ln", "log", "nsqrt"};

        return Arrays.asList(builtInFunctions).contains(function);

    }

}
