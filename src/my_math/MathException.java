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
 * @author Martin Kobelka (xkobel02@stud.fit.vutbr.cz)
 * @version 1.0
 *
 * @brief Class for mathematical exception
 *
 */
public class MathException extends Exception{

    private MathExceptionType type;

    /**
     * @brief Contructor of MathException
     *
     * @param type Type of error
     */
    public MathException(MathExceptionType type) {

        this.type = type;

    }

    public MathExceptionType getType() {
        return type;
    }
}
