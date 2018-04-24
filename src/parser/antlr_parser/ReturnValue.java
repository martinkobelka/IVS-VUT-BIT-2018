/**
 * Copyright 2018 Martin Kobelka (xkobel02@stud.fit.vutbr.cz)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package parser.antlr_parser;

/**
 * @author Martin Kobelka (xkobel02@stud.fit.vutbr.cz)
 * @version 1.0
 *
 * @brief Class which represents return value from parser
 *
 * <p>
 *     Represents value, which is return from parser. It can be use as linear list too, because
 *     there shoul be more return values
 * </p>
 */
public class ReturnValue {

    /**
     * Final value in double format
     */
    private double value;

    /**
     * Text representation of return value
     */
    private String textRepresentation;

    /**
     * Next value, if there is more values
     */
    public ReturnValue next;

    /**
     * Last value in list of values
     */
    public ReturnValue last;
    /**
     * Type of return Value
     */
    private TypeReturnValue typeReturnValue;

    /**
     * Create new return value
     */
    public ReturnValue() {
        value = 0.0;
        textRepresentation = "0.0";
        next = null;
        last = null;
        typeReturnValue = TypeReturnValue.OK;
    }

    /***
     * Set next value
     * @param returnValue
     */
    public void setNext(ReturnValue returnValue) {

        if(this.next == null) {
            this.next = returnValue;
            this.last = this.next;
        }
        else {

            this.last.next = returnValue;
            this.last = this.last.next;
        }

    }

    /**
     * Create new return value
     *
     * @param value value in double format
     * @param textRepresentation string representation (latex format)
     */
    public ReturnValue(double value, String textRepresentation) {
        this(value, textRepresentation, TypeReturnValue.OK);
    }

    public ReturnValue(double value, String textRepresentation, TypeReturnValue typeReturnValue) {
        this.value = value;
        this.textRepresentation = textRepresentation;
        next = null;
        last = null;
        this.typeReturnValue = typeReturnValue;
    }

    /**
     * Get final value of double format
     * @return
     */
    public double getValue() {
        return value;
    }

    /**
     * Ser final value in double format
     *
     * @param value
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Get text repsentation of parsing expression (in latex format)
     *
     * @return
     */
    public String getTextRepresentation() {
        return textRepresentation;
    }

    /**
     * @brief Set text representation
     *
     * <p>
     *     Setter for {@code this.textRepresentation}
     * </p>
     *
     * @param textRepresentation
     */
    public void setTextRepresentation(String textRepresentation) {
        this.textRepresentation = textRepresentation;
    }

    /**
     * Get next value
     *
     * @return
     */
    public ReturnValue getNext() {
        return next;
    }

    /**
     * Getter for returnValue
     * @return
     */
    public TypeReturnValue getTypeReturnValue() {
        return typeReturnValue;
    }

    /**
     * Setter for returnValue
     * @param typeReturnValue
     */
    public void setTypeReturnValue(TypeReturnValue typeReturnValue) {
        this.typeReturnValue = typeReturnValue;
    }

}
