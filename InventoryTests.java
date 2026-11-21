public class InventoryTests {
    private static int checks = 0;

    private static void check(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
        checks++;
    }

    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        check(inventory.searchItem(101) == null, "Empty inventory");
        check(inventory.addItem(101, "Notebook", "Stationery", 4.99, "PaperCo", 10), "Add item");
        check(!inventory.addItem(101, "Duplicate", "Stationery", 3.0, "PaperCo", 1), "Duplicate SKU");
        check(!inventory.addItem(102, "Bad", "Stationery", -1, "PaperCo", 1), "Negative price");
        check(!inventory.addItem(102, "Bad", "Stationery", 1, "PaperCo", -1), "Negative stock");
        check(inventory.searchItem(101) != null, "Search existing item");
        check(inventory.searchItem(999) == null, "Search missing item");
        check(inventory.sellItem(101, 3), "Sell item");
        check(inventory.searchItem(101).getQuantity() == 7, "Stock decreases");
        check(!inventory.sellItem(101, 8), "Prevent oversell");
        check(!inventory.sellItem(101, -1), "Prevent negative sale");
        check(!inventory.sellItem(999, 1), "Prevent missing sale");
        check(inventory.restockItem(101, 5), "Restock item");
        check(inventory.searchItem(101).getQuantity() == 12, "Stock increases");
        check(!inventory.restockItem(101, 0), "Prevent zero restock");
        check(!inventory.restockItem(999, 1), "Prevent missing restock");
        check(inventory.addItem(103, "Pencil", "Stationery", 1.25, "PaperCo", Integer.MAX_VALUE), "Max stock accepted");
        check(!inventory.restockItem(103, 1), "Prevent stock overflow");
        check(inventory.printItems("stationery") == 2, "Case-insensitive department filter");
        check(inventory.printItems("Unknown") == 0, "Unknown department");
        System.out.println("All " + checks + " tests passed.");
    }
}
