package my_math;


/**
 * This interface is instructions for implementator of math library
 * There should be private functions for every math operation too.
 */
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
    double run_operate(double[] operands, Operation operation) throws MathException;

    /**
     * Return a constant value
     * @param constant Type of contant
     * @return Constant value
     */
    double return_constant(Type_of_constant constant);



}
