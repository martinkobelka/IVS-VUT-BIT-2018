package tests;

import org.junit.*;
import my_math.*;

import static org.junit.Assert.*;

public class My_math_interfaceTest {

    private My_math_interface my_math;
    private int operation_count = 0;
    private int constant_count = 0;
    private double accuracy;

    @Before
    public void setUp() {
        my_math = new My_math();
        accuracy = 0.0000000001;
    }

    @Test
    public void contant_pi() {
        assertEquals(Math.PI, my_math.return_constant(Type_of_constant.PI), 0.0);
        constant_count++;
    }

    @Test
    public void contant_euler() {
        assertEquals(Math.E, my_math.return_constant(Type_of_constant.EULER_NUMBER), 0.0);
        constant_count++;
    }

    /*----------------------ADD-Tests---------------------------------*/
    @Test
    public void add() throws Exception {
        operation_count++;
        double[] operands = {5.4, 4.6};
        assertEquals(10, my_math.run_operate(operands, Operation.ADD), accuracy);
        operands = new double[]{2, -2};
        assertEquals(0, my_math.run_operate(operands, Operation.ADD), accuracy);
        operands = new double[]{-10, -15};
        assertEquals(-25, my_math.run_operate(operands, Operation.ADD), accuracy);
    }

    @Test
    public void add_failure() throws Exception {
        double[] operands = {40, 120};
        assertFalse(0 == my_math.run_operate(operands, Operation.ADD));
    }

    @Test(expected = MathException.class)
    public void add_3_arguments_failure() throws Exception {
        double[] operands = {40, 120, 100};
        my_math.run_operate(operands, Operation.ADD);
    }
    /*-----------------------Substract-tests-----------------------*/
    @Test
    public void subtract() throws Exception {
        operation_count++;
        double[] operands = {10.23, 10.14};
        assertEquals(0.09, my_math.run_operate(operands, Operation.SUBTRACT), accuracy);
        operands = new double[]{-20, -40};
        assertEquals(20, my_math.run_operate(operands, Operation.SUBTRACT), accuracy);
        operands = new double[]{-15.15, -15.15};
        assertEquals(0, my_math.run_operate(operands, Operation.SUBTRACT), accuracy);
    }

    @Test
    public void subtract_failure() throws Exception {
        double[] operands = {100, 60};
        assertFalse(4 == my_math.run_operate(operands, Operation.SUBTRACT));
    }

    @Test(expected = MathException.class)
    public void subtract_3_arguments_failure() throws Exception {
        double[] operands = {40, 120, 100};
        my_math.run_operate(operands, Operation.SUBTRACT);
    }
    /*-----------------------Multiply-tests-------------------------*/
    @Test
    public void multiply() throws Exception {
        operation_count++;
        double[] operands = {1.3, 100};
        assertEquals(103, my_math.run_operate(operands, Operation.MULTIPLY), accuracy);
        operands = new double[]{0, 15.324};
        assertEquals(0, my_math.run_operate(operands, Operation.MULTIPLY), accuracy);
        operands = new double[]{-12, 12};
        assertEquals(-144, my_math.run_operate(operands, Operation.MULTIPLY), accuracy);
        operands = new double[]{-5, -4};
        assertEquals(20, my_math.run_operate(operands, Operation.MULTIPLY), accuracy);
    }

    @Test
    public void multiply_failure() throws Exception {
        double[] operands = {3, 5};
        assertFalse(-15 == my_math.run_operate(operands, Operation.MULTIPLY));
    }

    @Test(expected = MathException.class)
    public void multiply_3_arguments_failure() throws Exception {
        double[] operands = {40, 120, 100};
        my_math.run_operate(operands, Operation.MULTIPLY);
    }
    /*-------------------------Divide-tests-------------------------*/
    @Test(expected = MathException.class)
    public void divide_by_zero() throws Exception {
        operation_count++;
        double[] operands = {10, 0};
        my_math.run_operate(operands, Operation.DIVIDE);
    }

    @Test
    public void divide() throws Exception {
        double[] operands = {10.123, 10.123};
        assertEquals(1, my_math.run_operate(operands, Operation.DIVIDE), accuracy);
        operands = new double[]{-20.5, 10};
        assertEquals(-2.05, my_math.run_operate(operands, Operation.DIVIDE), accuracy);
        operands = new double[]{-60, -12};
        assertEquals(5, my_math.run_operate(operands, Operation.DIVIDE), accuracy);
        operands = new double[]{10.1, -2.5};
        assertEquals(-4.04, my_math.run_operate(operands, Operation.DIVIDE), accuracy);
        operands = new double[]{0, 23.22};
        assertEquals(0, my_math.run_operate(operands, Operation.DIVIDE), accuracy);
    }

    @Test
    public void divide_failure() throws Exception {
        double[] operands = {169, 13};
        assertFalse(1.3 == my_math.run_operate(operands, Operation.DIVIDE));
    }

    @Test(expected = MathException.class)
    public void divide_3_arguments_failure() throws Exception {
        double[] operands = {40, 120, 100};
        my_math.run_operate(operands, Operation.DIVIDE);
    }
    /*--------------------------Sin-tests--------------------------*/
    @Test
    public void sin() throws Exception {
        operation_count++;
        double[] operands = {0.98};
        assertEquals(0.83049737049, my_math.run_operate(operands, Operation.SIN), accuracy);
        operands = new double[]{0};
        assertEquals(0, my_math.run_operate(operands, Operation.SIN), accuracy);
        operands = new double[]{Math.PI};
        assertEquals(1, my_math.run_operate(operands, Operation.SIN), accuracy);
    }

    @Test(expected = MathException.class)
    public void sin_2_arguments_failure() throws Exception {
        double[] operands = {0.98, 1.5};
        my_math.run_operate(operands, Operation.SIN);
    }

    @Test
    public void sin_failure() throws Exception {
        double[] operands = {12};
        assertFalse(3 == my_math.run_operate(operands, Operation.SIN));
    }

    @Test
    public void sin_periodicity() throws Exception {
        double[] operands = {0.425};
        double[] operands2 = {0.425 + 2 * Math.PI};
        assertTrue(my_math.run_operate(operands2, Operation.SIN) == my_math.run_operate(operands, Operation.SIN));
    }
    /*-------------------------Cos-tests----------------------------*/
    @Test
    public void cos() throws Exception {
        operation_count++;
        double[] operands = {2.45};
        assertEquals(-0.77023125405, my_math.run_operate(operands, Operation.COS), accuracy);
        operands = new double[]{0};
        assertEquals(1, my_math.run_operate(operands, Operation.COS), accuracy);
        operands = new double[]{Math.PI};
        assertEquals(0, my_math.run_operate(operands, Operation.COS), accuracy);
    }

    @Test(expected = MathException.class)
    public void cos_2_arguments_failure() throws Exception {
        double[] operands = {1.33, 13.313};
        my_math.run_operate(operands, Operation.COS);
    }

    @Test
    public void cos_failure() throws Exception {
        double[] operands = {5};
        assertFalse(4 == my_math.run_operate(operands, Operation.COS));
    }

    @Test
    public void cos_periodicity() throws Exception {
        double[] operands = {0.425};
        double[] operands2 = {0.425 + 2 * Math.PI};
        assertTrue(my_math.run_operate(operands2, Operation.COS) == my_math.run_operate(operands, Operation.COS));
    }
    /*------------------------Tag-tests------------------------------*/
    @Test
    public void tag() throws Exception {
        operation_count++;
        double[] operands = {1.54};
        assertEquals(32.46113891286, my_math.run_operate(operands, Operation.TAG), accuracy);
        operands = new double[]{Math.PI};
        assertEquals(0, my_math.run_operate(operands, Operation.TAG), accuracy);
    }

    @Test(expected = MathException.class)
    public void tag_failure_half_pi() throws Exception {
        double[] operands = {Math.PI / 2};
        my_math.run_operate(operands, Operation.TAG);
    }

    @Test(expected = MathException.class)
    public void tag_2_arguments_failure() throws Exception {
        double[] operands = {5.32, 14.463};
        my_math.run_operate(operands, Operation.TAG);
    }

    @Test
    public void tag_failure() throws Exception {
        double[] operands = {23.321};
        assertFalse(1 == my_math.run_operate(operands, Operation.TAG));
    }

    @Test
    public void tag_periodicity() throws Exception {
        double[] operands = {0.425};
        double[] operands2 = {0.425 + 2 * Math.PI};
        assertTrue(my_math.run_operate(operands2, Operation.TAG) == my_math.run_operate(operands, Operation.TAG));
    }
    /*-----------------------Cotg------------------------------------*/
    @Test
    public void cotag() throws Exception {
        operation_count++;
        double[] operands = {1.23};
        assertEquals(0.35463310168, my_math.run_operate(operands, Operation.COTG), accuracy);
        operands = new double[]{Math.PI * 3 / 2};
        assertEquals(0, my_math.run_operate(operands, Operation.COTG), accuracy);
    }


    @Test(expected = MathException.class)
    public void cotag_failure_pi() throws Exception {
        double[] operands = {Math.PI};
        my_math.run_operate(operands, Operation.COTG);
    }

    @Test(expected = MathException.class)
    public void cotag_2_arguments_failure() throws Exception {
        double[] operands = {3, 4.463};
        my_math.run_operate(operands, Operation.COTG);
    }

    @Test
    public void cotg_failure() throws Exception {
        double[] operands = {0.348};
        assertFalse(1 == my_math.run_operate(operands, Operation.COTG));
    }

    @Test
    public void cotg_periodicity() throws Exception {
        double[] operands = {0.425};
        double[] operands2 = {0.425 + 2 * Math.PI};
        assertTrue(my_math.run_operate(operands2, Operation.COTG) == my_math.run_operate(operands, Operation.COTG));
    }
    /*-------------------------Factorial------------------------------*/
    @Test
    public void fact() throws Exception {
        operation_count++;
        double[] operators = {4};
        assertEquals(24, my_math.run_operate(operators, Operation.FACTORIAL), accuracy);
        operators = new double[]{0};
        assertEquals(1, my_math.run_operate(operators, Operation.FACTORIAL), accuracy);
    }

    @Test(expected = MathException.class)
    public void fact_not_int_failure() throws Exception {
        double[] operands = {1.5};
        my_math.run_operate(operands, Operation.FACTORIAL);
    }

    @Test(expected = MathException.class)
    public void fact_negative_integer_failure() throws MathException {
        double[] operands = {-1};
        my_math.run_operate(operands, Operation.FACTORIAL);
    }

    @Test(expected = MathException.class)
    public void fact_overflow_failure() throws MathException {
        double MAXFACT = 170;
        double[] operands = {MAXFACT + 1};
        my_math.run_operate(operands, Operation.FACTORIAL);
    }

    @Test(expected = MathException.class)
    public void fact_2_arguments_failure() throws MathException {
        double[] operands = {1, 2};
        my_math.run_operate(operands, Operation.FACTORIAL);
    }

    @Test
    public void fact_failure() throws Exception {
        double[] operands = {5};
        assertFalse(5 == my_math.run_operate(operands, Operation.FACTORIAL));
    }

    /*------------------------Sqrt------------------------------------*/
    @Test
    public void sqrt() throws Exception {
        operation_count++;
        double[] operators = {4};
        assertEquals(2, my_math.run_operate(operators, Operation.SQRT), accuracy);
        operators = new double[]{0};
        assertEquals(0, my_math.run_operate(operators, Operation.SQRT), accuracy);
        operators = new double[]{1};
        assertEquals(1, my_math.run_operate(operators, Operation.SQRT), accuracy);
    }

    @Test(expected = MathException.class)
    public void sqrt_negative_number_failure() throws MathException {
        double[] operands = {-1};
        my_math.run_operate(operands, Operation.SQRT);
    }

    @Test(expected = MathException.class)
    public void sqrt_2_arguments_failure() throws MathException {
        double[] operands = {1, 2};
        my_math.run_operate(operands, Operation.SQRT);
    }

    @Test
    public void sqrt_failure() throws Exception {
        double[] operands = {16};
        assertFalse(-4 == my_math.run_operate(operands, Operation.SQRT));
    }

    /*------------------------Modulo----------------------------------*/
    @Test
    public void modulo() throws Exception {
        operation_count++;
        double[] operators = {4, 3};
        assertEquals(1, my_math.run_operate(operators, Operation.MODULO), accuracy);
        operators = new double[]{-4, 3};
        assertEquals(-1, my_math.run_operate(operators, Operation.MODULO), accuracy);
        operators = new double[]{-4, -3};
        assertEquals(-1, my_math.run_operate(operators, Operation.MODULO), accuracy);
        operators = new double[]{-23, -23};
        assertEquals(0, my_math.run_operate(operators, Operation.MODULO), accuracy);
        operators = new double[]{16, 42};
        assertEquals(16, my_math.run_operate(operators, Operation.MODULO), accuracy);
    }

    @Test(expected = MathException.class)
    public void modulo_not_integer_fail() throws Exception {
        double operands[] = {1.1, 2};
        my_math.run_operate(operands, Operation.MODULO);
    }

    @Test(expected = MathException.class)
    public void modulo_3_arguments_failure() throws MathException {
        double[] operands = {1, 2, 3};
        my_math.run_operate(operands, Operation.MODULO);
    }

    @Test
    public void modulo_failure() throws Exception {
        double[] operands = {3, 5};
        assertFalse(0 == my_math.run_operate(operands, Operation.MODULO));
    }
    /*------------------------Enum-Operations-------------------------*/
    @Test
    public void number_of_tested_operations() {
        assertEquals(operation_count, Operation.values().length);
    }

    @Test
    public void number_of_tested_constants() {
        assertEquals(constant_count, Type_of_constant.values().length);
    }
}