import java.util.Scanner;

public class InventoryApp {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        inventory.addItem(101, "Notebook", "Stationery", 4.99, "PaperCo", 30);
        inventory.addItem(102, "Headphones", "Electronics", 29.99, "AudioWorks", 12);
        inventory.addItem(103, "Water Bottle", "Accessories", 14.50, "Everyday", 20);
        inventory.addItem(104, "Pencils", "Stationery", 2.49, "PaperCo", 50);

        Scanner scanner = new Scanner(System.in);
        System.out.println("=== INVENTORY MANAGEMENT SYSTEM ===");
        boolean running = true;
        while (running) {
            System.out.println("\n1. View all products\n2. Search by SKU\n3. View by department");
            System.out.println("4. Sell product\n5. Restock product\n6. Add product\n7. Exit");
            int choice = readInt(scanner, "Choose an option: ");
            switch (choice) {
                case 1:
                    inventory.printItems(null);
                    break;
                case 2: {
                    int sku = readInt(scanner, "SKU: ");
                    Inventory.Item item = inventory.searchItem(sku);
                    if (item == null) System.out.println("Product not found.");
                    else Inventory.printItem(item);
                    break;
                }
                case 3:
                    System.out.print("Department: ");
                    inventory.printItems(scanner.nextLine());
                    break;
                case 4: {
                    int sku = readInt(scanner, "SKU: ");
                    int quantity = readInt(scanner, "Quantity to sell: ");
                    System.out.println(inventory.sellItem(sku, quantity)
                            ? "Sale completed." : "Sale failed: check SKU and available stock.");
                    break;
                }
                case 5: {
                    int sku = readInt(scanner, "SKU: ");
                    int quantity = readInt(scanner, "Quantity to add: ");
                    System.out.println(inventory.restockItem(sku, quantity)
                            ? "Product restocked." : "Restock failed: check SKU and quantity.");
                    break;
                }
                case 6: {
                    int sku = readInt(scanner, "New SKU: ");
                    System.out.print("Product name: ");
                    String name = scanner.nextLine();
                    System.out.print("Department: ");
                    String department = scanner.nextLine();
                    double price = readDouble(scanner, "Price: ");
                    System.out.print("Supplier: ");
                    String supplier = scanner.nextLine();
                    int quantity = readInt(scanner, "Starting stock: ");
                    System.out.println(inventory.addItem(sku, name, department, price, supplier, quantity)
                            ? "Product added." : "Could not add product. Check fields and SKU uniqueness.");
                    break;
                }
                case 7:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Please select a number from 1 to 7.");
            }
        }
        scanner.close();
    }

    // Keeps asking until the user enters a whole number.
    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    // Keeps asking until the user enters a valid price.
    private static double readDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            try {
                double result = Double.parseDouble(value);
                if (Double.isFinite(result)) return result;
            } catch (NumberFormatException e) {
                // Invalid input is handled by the message below.
            }
            System.out.println("Please enter a valid number.");
        }
    }
}
