/**
 * Copyright 2018 Martin Kobelka (xkobel02@stud.fit.vutbr.cz)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package my_math;

/**
 * Math library
 *
 * library implements every operation specified by enum Operation
 * @author Karpíšek Jakub
 */
public class My_math implements My_math_interface{

    /**
     * Run math operation
     *
     * @param operands Array of operands
     * @param operation type of operation
     *
     * @return result of chosen operation
     * @throws MathException when operands are not apropriate
     */
    @Override
    public double run_operate(double[] operands, Operation operation) throws MathException {

        // Return result according operation
        switch(operation){
            case ADD: return add(operands);
            case SUBTRACT: return sub(operands);
            case MULTIPLY: return mul(operands);
            case DIVIDE: return div(operands);
            case SIN: return sin(operands);
            case COS: return cos(operands);
            case TAG: return tan(operands);
            case COTG: return cotg(operands);
            case FACTORIAL: return fac(operands);
            case SQRT: return sqrt(operands);
            case MODULO: return mod(operands);
            case POWER: return power(operands);
            case LN: return ln(operands);
            case LOG: return log10(operands);

            default: throw new MathException(MathExceptionType.ERR_TYPE);
        }
    }

    /**
     * Return value of constant
     *
     * @param constant Type of contant
     * @return constant from mathematical library
     */
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
     * Power
     *
     * @param operands contains two operands for this operation
     * @return operand[0] ^ operand[1]
     */
    private double power(double[] operands) throws MathException{

        if(operands.length != 2) {
            throw new MathException(MathExceptionType.ERR_OPERANDS);
        }

        return Math.pow(operands[0], operands[1]);

    }


    /**
     * Addition
     *
     * @param operands contains two operands for this operation
     * @return sum of two operands
     * @throws MathException if less or more than two operands are provided
     */
    private double add(double[] operands)throws MathException{
        if(operands.length != 2) {
            throw new MathException(MathExceptionType.ERR_OPERANDS);
        }
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
        if(operands.length != 2) {
            throw new MathException(MathExceptionType.ERR_OPERANDS);
        }
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
        if(operands.length != 2) {
            throw new MathException(MathExceptionType.ERR_OPERANDS);
        }
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
        if(operands.length != 2) {
            throw new MathException(MathExceptionType.ERR_OPERANDS);
        }
        if(operands[1] == 0) {
            throw new MathException(MathExceptionType.ERR_ZERO);
        }
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
        if(operands.length != 1) {
            throw new MathException(MathExceptionType.ERR_OPERANDS);
        }
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
        if(operands.length != 1) {
            throw new MathException(MathExceptionType.ERR_OPERANDS);
        }
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
        if(operands.length != 1) {
            throw new MathException(MathExceptionType.ERR_OPERANDS);
        }
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

        if(operands.length != 1) {
            throw new MathException(MathExceptionType.ERR_OPERANDS);
        }
        return 1 / Math.tan(operands[0]);
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

        if(operands.length != 1) {
            throw new MathException(MathExceptionType.ERR_OPERANDS);
        }
        if(operands[0] < 0) {
            throw new MathException(MathExceptionType.ERR_NG_ZERO);
        }
        if(operands[0] != Math.floor(operands[0])) {
            throw new MathException(MathExceptionType.ERR_NOT_INT);
        }

        double result = 1.0;

        for(double i = 1; i <= operands[0]; i++) {

            result *= i;
            if (result >= Double.MAX_VALUE) {
                throw new MathException(MathExceptionType.ERR_FACT_OVERFLOW);
            }
        }
        return result;
    }

    /**
     * Square (n-th) root
     *
     * @param operands contains one or two operands for this operation
     *                 one operand:
     *                      square root for value operand[0]
     *                 two operands:
     *                      operand[0] is base
     *                      operand[1] is value
     * @return n-th root
     * @throws MathException if less or more than one operand is provided
     * or if operands are out of range <0, inf)
     */
    private double sqrt(double[] operands) throws MathException{
        switch(operands.length){
            case 1: {
                if(operands[0] < 0) {
                    throw new MathException(MathExceptionType.ERR_NG_ZERO);
                }
                return Math.sqrt(operands[0]);
            }
            case 2: {
                if(operands[0] != Math.floor(operands[0])) {
                    throw new MathException(MathExceptionType.ERR_NOT_INT);
                }
                return nthrt((int)operands[0], operands[1]);
            }
            default: {
                throw new MathException(MathExceptionType.ERR_OPERANDS);
            }
        }
    }

    /**
     * N-th root
     *
     * Helper method for computing n-th root of a value
     *
     * @param base of root
     * @param value to get a root of
     * @return n-th root
     * @throws MathException if a negative value is provided for odd number base
     */
    private double nthrt(int base, double value)throws MathException{
        if(base % 2 == 0) {     //even number
            if(value < 0)
                throw new MathException(MathExceptionType.ERR_NG_ZERO);
        }
        switch(base){
            case 1: return value;
            case 2: return Math.sqrt(value);
            case 3: return Math.cbrt(value);
            case 4: return Math.sqrt(Math.sqrt(value));
            default: return Math.pow(value, 1.0/base);
        }
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
            throw new MathException(MathExceptionType.ERR_OPERANDS);
        if(operands[0] != Math.floor(operands[0]) || operands[1] != Math.floor(operands[1]))
            throw new MathException(MathExceptionType.ERR_NOT_INT);
        return (double)((int)operands[0] % (int)operands[1]);
    }

    /**
     * Natural logarithm base E
     *
     * @param operands contains one operand for this operation
     * @return ln(op1)
     * @throws MathException if less or more than one operand is provided
     * @throws MathException operand is not a positive number
     */
    private double ln(double[] operands)throws MathException{
        if(operands.length != 1)
            throw new MathException(MathExceptionType.ERR_OPERANDS);
        if(operands[0] <= 0)
            throw new MathException(MathExceptionType.ERR_NG_ZERO);
        return Math.log(operands[0]);
    }

    /**
     * Decadic logarithm base 10
     *
     * @param operands contains one operand for this operation
     * @return log10(op1)
     * @throws MathException if less or more than one operand is provided
     * @throws MathException operand is not a positive number
     */
    private double log10(double[] operands)throws MathException{
        if(operands.length != 1)
            throw new MathException(MathExceptionType.ERR_OPERANDS);
        if(operands[0] <= 0)
            throw new MathException(MathExceptionType.ERR_NG_ZERO);
        return Math.log10(operands[0]);
    }
}
