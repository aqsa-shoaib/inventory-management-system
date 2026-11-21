public class Inventory {
    private Item head;

    public static class Item {
        private final int sku;
        private final String name;
        private final String department;
        private final double price;
        private final String supplier;
        private int quantity;
        private Item next;

        public Item(int sku, String name, String department, double price, String supplier, int quantity) {
            this.sku = sku;
            this.name = name;
            this.department = department;
            this.price = price;
            this.supplier = supplier;
            this.quantity = quantity;
        }

        public int getSku() { return sku; }
        public String getName() { return name; }
        public String getDepartment() { return department; }
        public double getPrice() { return price; }
        public String getSupplier() { return supplier; }
        public int getQuantity() { return quantity; }
    }

    // Adds a product to the front of the linked list.
    public boolean addItem(int sku, String name, String department, double price, String supplier, int quantity) {
        if (sku <= 0 || name == null || name.isBlank() || department == null || department.isBlank()
                || supplier == null || supplier.isBlank() || !Double.isFinite(price) || price < 0
                || quantity < 0 || searchItem(sku) != null) {
            return false;
        }
        Item item = new Item(sku, name.trim(), department.trim(), price, supplier.trim(), quantity);
        item.next = head;
        head = item;
        return true;
    }

    // Searches the linked list for a matching SKU.
    public Item searchItem(int sku) {
        Item current = head;
        while (current != null) {
            if (current.sku == sku) return current;
            current = current.next;
        }
        return null;
    }

    // Reduces stock only when the requested quantity is available.
    public boolean sellItem(int sku, int quantity) {
        Item item = searchItem(sku);
        if (item == null || quantity <= 0 || item.quantity < quantity) return false;
        item.quantity -= quantity;
        return true;
    }

    // Adds stock to an existing product.
    public boolean restockItem(int sku, int quantity) {
        Item item = searchItem(sku);
        if (item == null || quantity <= 0 || quantity > Integer.MAX_VALUE - item.quantity) return false;
        item.quantity += quantity;
        return true;
    }

    // Displays matching products, or all products when department is null.
    public int printItems(String department) {
        int count = 0;
        Item current = head;
        while (current != null) {
            if (department == null || current.department.equalsIgnoreCase(department.trim())) {
                printItem(current);
                count++;
            }
            current = current.next;
        }
        if (count == 0) System.out.println("No matching products found.");
        return count;
    }

    public static void printItem(Item item) {
        System.out.printf("SKU: %d | %s | %s | $%.2f | Supplier: %s | Stock: %d%n",
                item.sku, item.name, item.department, item.price, item.supplier, item.quantity);
    }
}
