package my_math;

public interface My_math_interface {

    /**
     * Run math operation
     *
     * @param operands Array of operands
     * @param operation type of operation
     *
     * @return Result of operation
     *
     * @throws MathException Where operands or result of operation is not correct
     */
    public double operate(double[] operands, Operation operation) throws MathException;


}
