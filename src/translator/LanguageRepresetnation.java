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

            if (o instanceof Item && ((Item) o).getDepartment().equals(department) && ((Item) o).getItem().equals(item)) {
                return true;
            }
            else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return department.hashCode() + item.hashCode();
        }


    }

    private HashSet<Item> items;

    public LanguageRepresetnation() {
        items = new HashSet<>();
    }

    public void addItem(String department, String item, String value) {
        items.add(new Item(department, item, value));
    }

    public String getItem(String department, String item) throws LanguageException {
        for(Item i : items) {
            if (i.equals(new Item(department, item, ""))) {
                return i.getValue();
            }
        }

        throw new LanguageException(LanguageException.LanguageExceptionType.NO_TRANSLATION);
    }



}
