package tests;

import org.junit.*;
import my_math.*;

import static org.junit.Assert.*;

public class My_math_interfaceTest {

    private My_math_interface my_math;
    private int operation_count;
    private double accuracy;

    @BeforeClass
    public void setUp() throws Exception {
        my_math = new My_math();
        operation_count = 0;
        accuracy = 0.00001;
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void contant_pi() throws Exception {
        assertEquals(Math.PI, my_math.return_constant(Type_of_constant.PI));
    }

    @Test
    public void contant_euler() throws Exception {
        assertEquals(Math.E, my_math.return_constant(Type_of_constant.EULER_NUMBER));
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
        assertEquals(0.830497, my_math.run_operate(operands, Operation.SIN), accuracy);
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

    /*-------------------------Cos-tests----------------------------*/
    @Test
    public void cos() throws Exception {
        operation_count++;
        double[] operands = {2.45};
        assertEquals(-0.770231, my_math.run_operate(operands, Operation.COS), accuracy);
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

    /*------------------------Tag-tests------------------------------*/
    @Test
    public void tag() throws Exception {
        operation_count++;
        double[] operands = {1.54};
        assertEquals(32.461138, my_math.run_operate(operands, Operation.TAG), accuracy);
        operands = new double[]{Math.PI};
        assertEquals(0, my_math.run_operate(operands, Operation.TAG), accuracy);
    }

    @Ignore
//@Test(expected = MathException.class) //tangens pre pi / 2 nie je definovany ale neviem ci sa da ta hodnota dosiahnut
    public void tag_failure_half_pi() throws Exception { //treba to skusit az pri implementacii mat. kniznice a pripadne zmazat
        double[] operands = {Math.PI / 2};
        my_math.run_operate(operands, Operation.TAG);
    }

    @Test(expected = MathException.class)
    public void tag_2_arguments_failure() throws Exception {
        double[] operands = {5.32, 14.463};
        my_math.run_operate(operands, Operation.TAG);
    }

    /*-----------------------Cotg------------------------------------*/
    @Test
    public void cotag() throws Exception {
        operation_count++;
        double[] operands = {1.23};
        assertEquals(0.354633, my_math.run_operate(operands, Operation.COTG), accuracy);
        operands = new double[]{Math.PI * 3 / 2};
        assertEquals(0, my_math.run_operate(operands, Operation.COTG), accuracy);
    }

    @Ignore
//@Test(expected = MathException.class) //cotangens pre pi nie je definovany ale neviem ci sa da ta hodnota dosiahnut
    public void cotag_failure_pi() throws Exception { //treba to skusit az pri implementacii mat. kniznice a pripadne zmazat
        double[] operands = {Math.PI};
        my_math.run_operate(operands, Operation.COTG);
    }

    @Test(expected = MathException.class)
    public void cotag_2_arguments_failure() throws Exception {
        double[] operands = {3, 4.463};
        my_math.run_operate(operands, Operation.COTG);
    }

    /*------------------------Enum-Operations-------------------------*/
    @Test
    public void number_of_tested_operations() throws Exception {
        assertEquals(operation_count, Operation.values().length);
    }

}