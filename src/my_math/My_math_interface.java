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
