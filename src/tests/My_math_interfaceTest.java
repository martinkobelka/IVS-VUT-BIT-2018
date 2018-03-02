package tests;

import org.junit.After;
import org.junit.Before;
import my_math.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class My_math_interfaceTest {

    private My_math_interface my_math;
    private Operation operation;

    @Before
    public void setUp() throws Exception {
        my_math = new My_math();

    }

    @After
    public void tearDown() throws Exception {
        /**
         * Clear informations
         */
    }

    /**
     *
     * There will be test for another operation and its arguments
     */

    @Test
    public void contant_pi() throws Exception {
        assertEquals(Math.PI, my_math.return_constant(Type_of_constant.PI));
    }

    @Test
    public void contant_euler() throws Exception {
        assertEquals(Math.E, my_math.return_constant(Type_of_constant.EULER_NUMBER));
    }

    @Test
    public void add() throws Exception {
        double[] operands = {5, 2};
        operation = Operation.ADD;
        assertEquals(10, my_math.run_operate(operands, operation));
        operands = {2, -2};
        assertEquals(0, my_math.run_operate(operands, operation));
        operands = {-10, -15};
        assertEquals(-25, my_math.run_operate(operands, operation));
    }

    @Test(expected = MathException.class)
    public void add_over_limit() throws Exception {
        double[] operands = {Double.MAX_VALUE, 1};
        operation = Operation.ADD;
        my_math.run_operate(operands, operation);
    }

    @Test(expected = MathException.class)
    public void add_under_limit() throws Exception {
        double[] operands = {Double.MIN_VALUE, -1};
        operation = Operation.ADD;
        my_math.run_operate(operands, operation);
    }

    @Test
    public void substract() throws Exception {
        double[] operands = {10, 10};
        operation = Operation.SUBTRACT;
        assertEquals(0, my_math.run_operate(operands, operation));

    }

    @Test(expected = MathException.class)
    public void substract_over_limit() throws Exception {
        double[] operands = {Double.MAX_VALUE, -1};
        operation = Operation.SUBTRACT;
        my_math.run_operate(operands, operation);
    }

    @Test(expected = MathException.class)
    public void substract_under_limit() throws Exception {
        double[] operands = {Double.MIN_VALUE, 1};
        operation = Operation.SUBTRACT;
        my_math.run_operate(operands, operation);
    }

}