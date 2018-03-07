package my_math;

/**
 * Math library
 *
 * library implements every operation specified by enum Operation
 * @author KarpíšekJakub
 */
public class My_math implements My_math_interface{

    @Override
    public double run_operate(double[] operands, Operation operation) throws MathException {
        double result;
        switch(operation){
            case ADD: result = add(operands); break;
            case SUBTRACT: result = sub(operands); break;
            case MULTIPLY: result = mul(operands); break;
            case DIVIDE: result = div(operands); break;
            case SIN: result = sin(operands); break;
            case COS: result = cos(operands); break;
            case TAG: result = tan(operands); break;
            case COTG: result = cotg(operands); break;
            case FACTORIAL: result = fac(operands); break;
            case SQRT: result = sqrt(operands); break;
            case MODULO: result = mod(operands); break;
            default: throw new MathException("Unsupported type of operation.");
        }
        return result;
    }

    @Override
    public double return_constant(Type_of_constant constant) {
        switch(constant){
            case PI: return Math.PI;
            case EULER_NUMBER: return Math.E;
            default: break;
        }
        return 0;
    }

    /**
     * Addition
     *
     * @param operands contains two operands for this operation
     * @return sum of two operands
     * @throws MathException if less or more than two operands are provided
     */
    private double add(double[] operands)throws MathException{
        if(operands.length != 2)
            throw new MathException("Wrong amount of operands!");
        return operands[0] + operands[1];
    }

    /**
     * Subtraction
     *
     * @param operands contains two operands for this operation
     * @return op1 - op2
     * @throws MathException if less or more than two operands are provided
     */
    private double sub(double[] operands)throws MathException{
        if(operands.length != 2)
            throw new MathException("Wrong amount of operands!");
        return operands[0] - operands[1];
    }

    /**
     * Multiply
     *
     * @param operands contains two operands for this operation
     * @return op1 * op2
     * @throws MathException if less or more than two operands are provided
     */
    private double mul(double[] operands)throws MathException{
        if(operands.length != 2)
            throw new MathException("Wrong amount of operands!");
        return operands[0] * operands[1];
    }

    /**
     * Division
     *
     * @param operands contains two operands for this operation
     * @return op1 / op2
     * @throws MathException if less or more than two operands are provided
     * or if second operand is zero
     */
    private double div(double[] operands)throws MathException{
        if(operands.length != 2)
            throw new MathException("Wrong amount of operands!");
        if(operands[1] == 0)
            throw new MathException("Division by zero!");
        return operands[0] / operands[1];
    }

    /**
     * Sinus
     *
     * @param operands contains one operand for this operation
     * @return sinus of operand
     * @throws MathException if less or more than one operand is provided
     */
    private double sin(double[] operands)throws MathException{
        if(operands.length != 1)
            throw new MathException("Wrong amount of operands!");
        return Math.sin(operands[0]);
    }

    /**
     * Cosinus
     *
     * @param operands contains one operand for this operation
     * @return cosinus of operand
     * @throws MathException if less or more than one operand is provided
     */
    private double cos(double[] operands)throws MathException{
        if(operands.length != 1)
            throw new MathException("Wrong amount of operands!");
        return Math.cos(operands[0]);
    }

    /**
     * Tangens
     *
     * @param operands contains one operand for this operation
     * @return tangens of operand
     * @throws MathException if less or more than one operand is provided
     */
    private double tan(double[] operands)throws MathException{
        if(operands.length != 1)
            throw new MathException("Wrong amount of operands!");
        return Math.tan(operands[0]);
    }

    /**
     * Cotangens
     *
     * @param operands contains one operand for this operation
     * @return cotangens of operand
     * @throws MathException if less or more than one operand is provided
     */
    private double cotg(double[] operands)throws MathException{
        if(operands.length != 1)
            throw new MathException("Wrong amount of operands!");
        return 1/Math.tan(operands[0]);
    }

    /**
     * Factorial
     *
     * @param operands contains one operand for this operation
     * @return factorial of operand
     * @throws MathException if less or more than one operand is provided
     * or if operands are not integer numbers
     * or if operands are out of range <0, inf)
     */
    private double fac(double[] operands)throws MathException{
        if(operands.length != 1)
            throw new MathException("Wrong amount of operands!");
        if(operands[0] < 0)
            throw new MathException("Input not greater than zero!");
        if(operands[0] != Math.floor(operands[0]))
            throw new MathException("Input is not integer!");
        int result = 1;
        for(int i = 1; i <= operands[0]; i++)
            result *= i;
        return result;
    }

    /**
     * Square root
     *
     * @param operands contains one operand for this operation
     * @return square root of operand
     * @throws MathException if less or more than one operand is provided
     * or if operands are out of range <0, inf)
     */
    private double sqrt(double[] operands)throws MathException{
        if(operands.length == 1)
            throw new MathException("Wrong amount of operands!");
        if(operands[0] < 0)
            throw new MathException("Input not greater than zero!");
        return Math.sqrt(operands[0]);
    }

    /**
     * Modulo
     *
     * @param operands contains two operands for this operation
     * @return op1 mod op2
     * @throws MathException if less or more than two operands are provided
     * or if operands are not integer numbers
     */
    private double mod(double[] operands)throws MathException{
        if(operands.length != 2)
            throw new MathException("Wrong amount of operands!");
        if(operands[0] != Math.floor(operands[0]) || operands[1] != Math.floor(operands[1]))
            throw new MathException("Input is not integer!");
        return (int)operands[0] % (int)operands[1];
    }
}
