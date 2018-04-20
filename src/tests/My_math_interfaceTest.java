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
package tests;

/**
 * @author Jan Vavro (xvavro05@stud.fit.vutbr.cz)
 *
 * Unit tests of mathematical library
 */

import my_math.*;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class My_math_interfaceTest {

    private My_math_interface my_math;
    public static int operation_count = 0;
    /**
     * Attribute for testing number of tested operations
     */
    public static int constant_count = 0;
    /**
     * Attribute for testing number of tested constants
     */
    private double accuracy;

    /** accuracy of result */

    @Before
    public void setUp() {
        my_math = new My_math();
        accuracy = 0.0000000001;
    }

    /**
     * Testing PI constant
     */
    @Test
    public void contant_pi() {
        assertEquals(Math.PI, my_math.return_constant(Type_of_constant.PI), 0.0);
        constant_count++;
    }

    /**
     * Testing Euler number constant
     */
    @Test
    public void contant_euler() {
        assertEquals(Math.E, my_math.return_constant(Type_of_constant.EULER_NUMBER), 0.0);
        constant_count++;
    }

    /*----------------------ADD-Tests---------------------------------*/

    /**
     * Testing basic functionality of add operation
     */
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

    /**
     * Testing fail of add operation
     */
    @Test
    public void add_failure() throws Exception {
        double[] operands = {40, 120};
        assertFalse(0 == my_math.run_operate(operands, Operation.ADD));
    }

    /**
     * Testing if wrong count of operands throws an exception
     */
    @Test(expected = MathException.class)
    public void add_3_arguments_failure() throws Exception {
        double[] operands = {40, 120, 100};
        my_math.run_operate(operands, Operation.ADD);
    }
    /*-----------------------Substract-tests-----------------------*/

    /**
     * Testing basic functionality of sub operation
     */
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

    /**
     * Testing fail of substract operation
     */
    @Test
    public void subtract_failure() throws Exception {
        double[] operands = {100, 60};
        assertFalse(4 == my_math.run_operate(operands, Operation.SUBTRACT));
    }

    /**
     * Testing if wrong count of operands throws an exception
     */
    @Test(expected = MathException.class)
    public void subtract_3_arguments_failure() throws Exception {
        double[] operands = {40, 120, 100};
        my_math.run_operate(operands, Operation.SUBTRACT);
    }
    /*-----------------------Multiply-tests-------------------------*/

    /**
     * Testing basic functionality of multiply operation
     */
    @Test
    public void multiply() throws Exception {
        operation_count++;
        double[] operands = {1.3, 100};
        assertEquals(130, my_math.run_operate(operands, Operation.MULTIPLY), accuracy);
        operands = new double[]{0, 15.324};
        assertEquals(0, my_math.run_operate(operands, Operation.MULTIPLY), accuracy);
        operands = new double[]{-12, 12};
        assertEquals(-144, my_math.run_operate(operands, Operation.MULTIPLY), accuracy);
        operands = new double[]{-5, -4};
        assertEquals(20, my_math.run_operate(operands, Operation.MULTIPLY), accuracy);
    }

    /**
     * Testing fail of multiply operation
     */
    @Test
    public void multiply_failure() throws Exception {
        double[] operands = {3, 5};
        assertFalse(-15 == my_math.run_operate(operands, Operation.MULTIPLY));
    }

    /**
     * Testing if wrong count of operands throws an exception
     */
    @Test(expected = MathException.class)
    public void multiply_3_arguments_failure() throws Exception {
        double[] operands = {40, 120, 100};
        my_math.run_operate(operands, Operation.MULTIPLY);
    }
    /*-------------------------Divide-tests-------------------------*/

    /**
     * Testing if dividing by zero throws an exception
     */
    @Test(expected = MathException.class)
    public void divide_by_zero() throws Exception {
        operation_count++;
        double[] operands = {10, 0};
        my_math.run_operate(operands, Operation.DIVIDE);
    }

    /**
     * Testing basic functionality of divide operation
     */
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

    /**
     * Testing fail of divide operation
     */
    @Test
    public void divide_failure() throws Exception {
        double[] operands = {169, 13};
        assertFalse(1.3 == my_math.run_operate(operands, Operation.DIVIDE));
    }

    /**
     * Testing if wrong count of operands throws an exception
     */
    @Test(expected = MathException.class)
    public void divide_3_arguments_failure() throws Exception {
        double[] operands = {40, 120, 100};
        my_math.run_operate(operands, Operation.DIVIDE);
    }
    /*--------------------------Sin-tests--------------------------*/

    /**
     * Testing basic functionality of sinus operation
     */
    @Test
    public void sin() throws Exception {
        operation_count++;
        double[] operands = {0.98};
        assertEquals(0.83049737049, my_math.run_operate(operands, Operation.SIN), accuracy);
        operands = new double[]{0};
        assertEquals(0, my_math.run_operate(operands, Operation.SIN), accuracy);
        operands = new double[]{Math.PI / 2};
        assertEquals(1, my_math.run_operate(operands, Operation.SIN), accuracy);
    }

    /**
     * Testing if wrong count of operands throws an exception
     */
    @Test(expected = MathException.class)
    public void sin_2_arguments_failure() throws Exception {
        double[] operands = {0.98, 1.5};
        my_math.run_operate(operands, Operation.SIN);
    }

    /**
     * Testing fail of sinus operation
     */
    @Test
    public void sin_failure() throws Exception {
        double[] operands = {12};
        assertFalse(3 == my_math.run_operate(operands, Operation.SIN));
    }

    /**
     * Testing periodicity of sinus operation
     */
    @Test
    public void sin_periodicity() throws Exception {
        double[] operands = {0.425};
        double[] operands2 = {0.425 + 2 * Math.PI};
        assertEquals(my_math.run_operate(operands2, Operation.SIN), my_math.run_operate(operands, Operation.SIN), accuracy);
    }
    /*-------------------------Cos-tests----------------------------*/

    /**
     * Testing basic functionality of cosinus operation
     */
    @Test
    public void cos() throws Exception {
        operation_count++;
        double[] operands = {2.45};
        assertEquals(-0.77023125405, my_math.run_operate(operands, Operation.COS), accuracy);
        operands = new double[]{0};
        assertEquals(1, my_math.run_operate(operands, Operation.COS), accuracy);
        operands = new double[]{Math.PI};
        assertEquals(-1, my_math.run_operate(operands, Operation.COS), accuracy);
    }

    /**
     * Testing if wrong count of operands throws an exception
     */
    @Test(expected = MathException.class)
    public void cos_2_arguments_failure() throws Exception {
        double[] operands = {1.33, 13.313};
        my_math.run_operate(operands, Operation.COS);
    }

    /**
     * Testing fail of cosinus operation
     */
    @Test
    public void cos_failure() throws Exception {
        double[] operands = {5};
        assertFalse(4 == my_math.run_operate(operands, Operation.COS));
    }

    /**
     * Testing periodicity of cosinus operation
     */
    @Test
    public void cos_periodicity() throws Exception {
        double[] operands = {0.425};
        double[] operands2 = {0.425 + 2 * Math.PI};
        assertEquals(my_math.run_operate(operands2, Operation.COS), my_math.run_operate(operands, Operation.COS), accuracy);
    }
    /*------------------------Tag-tests------------------------------*/

    /**
     * Testing basic functionality of tangens operation
     */
    @Test
    public void tag() throws Exception {
        operation_count++;
        double[] operands = {1.54};
        assertEquals(32.46113891286, my_math.run_operate(operands, Operation.TAG), accuracy);
        operands = new double[]{Math.PI};
        assertEquals(0, my_math.run_operate(operands, Operation.TAG), accuracy);
    }

    /**
     * Testing if wrong count of operands throws an exception
     */
    @Test(expected = MathException.class)
    public void tag_2_arguments_failure() throws Exception {
        double[] operands = {5.32, 14.463};
        my_math.run_operate(operands, Operation.TAG);
    }

    /**
     * Testing fail of tangens operation
     */
    @Test
    public void tag_failure() throws Exception {
        double[] operands = {23.321};
        assertFalse(1 == my_math.run_operate(operands, Operation.TAG));
    }

    /**
     * Testing periodicty of tangens operation
     */
    @Test
    public void tag_periodicity() throws Exception {
        double[] operands = {0.425};
        double[] operands2 = {0.425 + 2 * Math.PI};
        assertEquals(my_math.run_operate(operands2, Operation.TAG), my_math.run_operate(operands, Operation.TAG), accuracy);
    }
    /*-----------------------Cotg------------------------------------*/

    /**
     * Testing basic functionality of cotangens operation
     */
    @Test
    public void cotag() throws Exception {
        operation_count++;
        double[] operands = {1.23};
        assertEquals(0.35463310168, my_math.run_operate(operands, Operation.COTG), accuracy);
        operands = new double[]{Math.PI * 3 / 2};
        assertEquals(0, my_math.run_operate(operands, Operation.COTG), accuracy);
    }

    /**
     * Testing if wrong count of operands throws an exception
     */
    @Test(expected = MathException.class)
    public void cotag_2_arguments_failure() throws Exception {
        double[] operands = {3, 4.463};
        my_math.run_operate(operands, Operation.COTG);
    }

    /**
     * Testing fail of cotangens operation
     */
    @Test
    public void cotg_failure() throws Exception {
        double[] operands = {0.348};
        assertFalse(1 == my_math.run_operate(operands, Operation.COTG));
    }

    /**
     * Testing periodicity of cotangens operation
     */
    @Test
    public void cotg_periodicity() throws Exception {
        double[] operands = {0.425};
        double[] operands2 = {0.425 + 2 * Math.PI};
        assertEquals(my_math.run_operate(operands2, Operation.COTG), my_math.run_operate(operands, Operation.COTG), accuracy);
    }
    /*-------------------------Factorial------------------------------*/

    /**
     * Testing basic functionality of factorial operation
     */
    @Test
    public void fact() throws Exception {
        operation_count++;
        double[] operators = {4};
        assertEquals(24, my_math.run_operate(operators, Operation.FACTORIAL), accuracy);
        operators = new double[]{0};
        assertEquals(1, my_math.run_operate(operators, Operation.FACTORIAL), accuracy);
    }

    /**
     * Testing if factorial non-integer vaule throws an exception
     */
    @Test(expected = MathException.class)
    public void fact_not_int_failure() throws Exception {
        double[] operands = {1.5};
        my_math.run_operate(operands, Operation.FACTORIAL);
    }

    /**
     * Testing if factorial of negative number throws an exception
     */
    @Test(expected = MathException.class)
    public void fact_negative_integer_failure() throws MathException {
        double[] operands = {-1};
        my_math.run_operate(operands, Operation.FACTORIAL);
    }

    /**
     * Testing if overflow throws an exception
     */
    @Test(expected = MathException.class)
    public void fact_overflow_failure() throws MathException {
        double MAXFACT = 170;
        double[] operands = {MAXFACT + 1};
        my_math.run_operate(operands, Operation.FACTORIAL);
    }

    /**
     * Testing if wrong count of operands throws an exception
     */
    @Test(expected = MathException.class)
    public void fact_2_arguments_failure() throws MathException {
        double[] operands = {1, 2};
        my_math.run_operate(operands, Operation.FACTORIAL);
    }

    /**
     * Testing fail of factorial operation
     */
    @Test
    public void fact_failure() throws Exception {
        double[] operands = {5};
        assertFalse(5 == my_math.run_operate(operands, Operation.FACTORIAL));
    }

    /*------------------------Sqrt------------------------------------*/

    /**
     * Testing basic functionality of root operation
     */
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

    /**
     * Testing higher level roots
     */
    @Test
    public void sqrt_two_operands() throws Exception {
        double[] operators = {2, 4};
        assertEquals(2, my_math.run_operate(operators, Operation.SQRT), accuracy);
        operators = new double[]{3, 8};
        assertEquals(2, my_math.run_operate(operators, Operation.SQRT), accuracy);
        operators = new double[]{4, 16};
        assertEquals(2, my_math.run_operate(operators, Operation.SQRT), accuracy);
    }

    /**
     * Testing if root of negative number throws an exception
     */
    @Test(expected = MathException.class)
    public void sqrt_negative_number_failure() throws MathException {
        double[] operands = {-1};
        my_math.run_operate(operands, Operation.SQRT);
    }

    /**
     * Testing if wrong count of operands throws an exception
     */
    @Test(expected = MathException.class)
    public void sqrt_3_arguments_failure() throws MathException {
        double[] operands = {1, 2, 4};
        my_math.run_operate(operands, Operation.SQRT);
    }

    /**
     * Testing fail of sqrt operation
     */
    @Test
    public void sqrt_failure() throws Exception {
        double[] operands = {16};
        assertFalse(-4 == my_math.run_operate(operands, Operation.SQRT));
    }

    /**
     * Testing if wrong count of operands throws an exception
     */
    @Test(expected = MathException.class)
    public void sqrt_null_arguments_failure() throws Exception {
        double[] operands = {};
        my_math.run_operate(operands, Operation.SQRT);
    }

    /*------------------------Modulo----------------------------------*/

    /**
     * Testing basic functionality of modulo operation
     */
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

    /**
     * Testing if non-integer value throws an exception
     */
    @Test(expected = MathException.class)
    public void modulo_not_integer_fail() throws Exception {
        double operands[] = {1.1, 2};
        my_math.run_operate(operands, Operation.MODULO);
    }

    /**
     * Testing if wrong count of operands throws an exception
     */
    @Test(expected = MathException.class)
    public void modulo_3_arguments_failure() throws MathException {
        double[] operands = {1, 2, 3};
        my_math.run_operate(operands, Operation.MODULO);
    }

    /**
     * Testing fail of modulo operation
     */
    @Test
    public void modulo_failure() throws Exception {
        double[] operands = {3, 5};
        assertFalse(0 == my_math.run_operate(operands, Operation.MODULO));
    }
    /*------------------------Enum-Operations-------------------------*/

    /**
     * Testing if all operations were tested
     */
    @Test
    public void zz_last_test_number_of_tested_operations() {
        assertEquals(operation_count, Operation.values().length);
    }


    /**
     * Testing if all constants were tested
     */
    @Test
    public void zz_last_test_number_of_tested_constants() {
        assertEquals(constant_count, Type_of_constant.values().length);
    }
}