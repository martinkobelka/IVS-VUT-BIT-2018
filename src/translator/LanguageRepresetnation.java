package translator;


import java.util.HashSet;

public class LanguageRepresetnation {

    private class Item {

        private String department, item, value;

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

        @Override
        public boolean equals(Object o) {

            // Items are equal when department && items are the sammem
            return (
                    o instanceof Item &&
                    ((Item) o).getDepartment().equals(department) &&
                    ((Item) o).getItem().equals(item)
            );
        }

        @Override
        public int hashCode() {
            return department.hashCode() + item.hashCode();
        }


    }

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
