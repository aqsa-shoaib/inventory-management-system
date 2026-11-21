# Inventory Management System (Java)

A terminal-based inventory management program written in Java. It uses a **singly linked list** to store products and allows users to search for items, sell products, restock inventory, and browse departments.

## Features

- Display all products or filter by department
- Search for a product using its SKU
- Sell products and update available stock
- Restock products
- Add products with a unique SKU, name, department, price, supplier, and quantity
- Validate numeric input and prevent invalid quantities or duplicate SKUs
- Start with four sample products for demonstration

## Technologies

- Java (JDK 11 or newer)
- Object-oriented programming
- Singly linked lists
- Terminal input/output

## Run the program

Open a terminal in the project folder and enter:

```bash
javac Inventory.java InventoryApp.java InventoryTests.java
java InventoryApp
```

Select options from the terminal menu. The program starts with sample inventory, so its features can be tried immediately.

## Run the tests

```bash
java InventoryTests
```

The tests cover adding items, duplicate SKUs, searching, selling, restocking, invalid quantities, and department filtering.

## Project structure

```text
Inventory.java       Product records and linked-list operations
InventoryApp.java    Interactive terminal menu
InventoryTests.java  Automated checks for inventory operations
README.md            Project documentation
```

## How the linked list works

Each product is stored in an `Item` node with a reference to the next node. The `head` variable points to the first product. Adding an item puts it at the front of the list. Searching for a SKU, displaying items, and updating stock traverse the list until a matching product is found.

## Notes

Inventory is stored in memory while the program is running; changes reset when the app closes. This project is a learning-focused inventory simulator, not a production retail system.
