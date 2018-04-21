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
package translator;

/**
 *
 * Representation of one item in language
 *
 * @author Martin Kobelka (xkobel02@stud.fit.vutbr.cz)
 * @version 1.0
 *
 */
public class Item {

    /**
     * Name of epartment of item
     */
    private String department;

    /**
     * Name of item
     */
    private String item;

    /**
     * Actual value
     */
    private String value;

    /**
     * Get value of item
     * @return
     */
    public String getValue() {
        return value;
    }

    /**
     * Create new item
     * @param department
     * @param item
     * @param value
     */
    public Item(String department, String item, String value) {
        this.department = department;
        this.item = item;
        this.value = value;
    }

    /**
     * Get item
     * @return
     */
    public String getItem() {
        return item;
    }

    /**
     * Set item
     * @param item
     */
    public void setItem(String item) {
        this.item = item;
    }

    /**
     * Get department
     * @return
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Set department
     * @param department
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Test if o is equas ad actual item
     * @param o
     * @return
     */
    @Override public boolean equals(Object o) {

        // Items are equal when department && items are the sammem
        return (
                o instanceof Item &&
                        ((Item) o).getDepartment().equals(department) &&
                        ((Item) o).getItem().equals(item)
        );
    }

    /**
     * Get hashcode of actual item
     * @return
     */
    @Override public int hashCode() {
        return department.hashCode() + item.hashCode();
    }
}