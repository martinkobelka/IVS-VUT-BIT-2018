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

import java.util.HashSet;

/**
 *
 * Representation of one language
 *
 * @author Martin Kobelka (xkobel02@stud.fit.vutbr.cz)
 * @version 1.0
 *
 */
public class LanguageRepresetnation {

    /**
     * Items of actual LanguageRepresentation
     */
    private HashSet<Item> items;

    /**
     * Create new language representation
     */
    public LanguageRepresetnation() {
        items = new HashSet<>(); // Cretae new set
    }

    /**
     * Add new intem into language model
     * @param department department of item
     * @param item item name
     * @param value value
     */
    public void addItem(String department, String item, String value) {
        items.add(new Item(department, item, value));
    }

    /**
     * Get element from set of items
     * @param department depatment of item
     * @param item item name
     * @return text value of item
     * @throws LanguageException when item does not exists
     */
    public String getItem(String department, String item) throws LanguageException {

        // Test item, items are equals when department && item are the same
        Item testItem = new Item(department, item, "");

        // Loop over all items
        for(Item i : items) {
            if (i.equals(testItem)) {
                return i.getValue();
            }
        }

        // When there is no translation
        throw new LanguageException(LanguageException.LanguageExceptionType.NO_TRANSLATION);
    }
}
