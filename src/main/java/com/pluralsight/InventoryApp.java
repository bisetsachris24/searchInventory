package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class InventoryApp {

    // Scanner reads input the user types in the terminal
    static Scanner theScanner = new Scanner(System.in);


    static ArrayList<Product> inventory = getInventory();

    // main() is where Java starts running your program
    public static void main(String[] args) {

        // "choice" holds the number the user types (1–5)
        int choice;

        // do-while always runs the body at least once, so the menu shows right away
        do {
            System.out.println("\nWhat do you want to do?");
            System.out.println("1- List all products");
            System.out.println("2- Lookup a product by its id");
            System.out.println("3- Find products within a price range");
            System.out.println("4- Add a new product");
            System.out.println("5- Quit the application");
            System.out.print("Enter command: ");

            // Read the number the user typed
            choice = theScanner.nextInt();

            // Match the user's choice to the right method
            switch (choice) {
                case 1:
                    listProducts();   // print every product in the list
                    break;
                case 2:
                    lookupById();     // find one product by its ID number
                    break;
                case 3:
                    searchByPrice();  // find products inside a price range
                    break;
                case 4:
                    addProduct();     // add a brand-new product to the list
                    break;
                case 5:
                    System.out.println("Goodbye!");  // loop stops because choice == 5
                    break;
                default:
                    System.out.println("Invalid option. Please enter 1 - 5.");
            }

        } while (choice != 5); // keep looping until the user picks 5 to quit
    }

    private static ArrayList<Product> getInventory() {

        // Start with an empty list — we fill it from the file below
        ArrayList<Product> products = new ArrayList<>();

        try {
            // BufferedReader lets us read one full line at a time from the file
            BufferedReader reader = new BufferedReader(new FileReader("inventory.csv"));

            String line;

            // readLine() returns null when there are no more lines — that stops the loop
            while ((line = reader.readLine()) != null) {

                // Split the line at every pipe | character
                // "\\|" is needed because | is a special character in Java regex
                // Result example: parts[0]="4567"  parts[1]="Hammer"  parts[2]="19.49"
                String[] parts = line.split("\\|");

                // Convert each part to the correct data type
                int id       = Integer.parseInt(parts[0]);   // String → int
                String name  = parts[1];                     // already a String
                double price = Double.parseDouble(parts[2]); // String → double

                // Build a Product and add it to the list
                products.add(new Product(id, name, price));
            }

            // Always close the file when finished to release system resources
            reader.close();

        } catch (Exception e) {
            // If the file is missing or a line is malformed, print the problem
            // instead of crashing the whole program
            System.out.println("Error reading inventory.csv: " + e.getMessage());
        }

        // Return the completed list (may be empty if the file had problems)
        return products;
    }

    // -------------------------------------------------------------------------
    // listProducts()
    // Loops through every product in the inventory ArrayList and prints it.
    // void means this method does not return anything — it just prints.
    // -------------------------------------------------------------------------
    private static void listProducts() {
        System.out.println("\n--- Product List ---");

        // Enhanced for-loop: "p" holds one Product at a time on each pass
        for (Product p : inventory) {
            System.out.println(p);  // calls Product's toString() method
        }
    }

    // -------------------------------------------------------------------------
    // lookupById()
    // Asks the user for an ID, then searches the inventory for a match.
    // -------------------------------------------------------------------------
    private static void lookupById() {
        System.out.print("Enter product ID to search: ");
        int id = scanner.nextInt();

        // Loop through every product to find one whose ID matches
        for (Product p : inventory) {
            if (p.getId() == id) {
                System.out.println("Found: " + p);
                return; // stop searching once we find it
            }
        }

        // Only reaches here if no product matched
        System.out.println("No product found with ID: " + id);
    }

    // -------------------------------------------------------------------------
    // addProduct()
    // Asks the user for details and adds a new Product to the inventory list.
    // -------------------------------------------------------------------------
    private static void addProduct() {
        System.out.print("Enter new product ID: ");
        int id = scanner.nextInt();

        System.out.print("Enter product name: ");
        String name = scanner.next();  // reads one word; use nextLine() for spaces

        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();

        // Create the new Product and add it to the existing ArrayList
        inventory.add(new Product(id, name, price));
        System.out.println("Product added successfully!");
    }

    // -------------------------------------------------------------------------
    // searchByPrice()
    // Asks for a min and max price, then prints every product in that range.
    // -------------------------------------------------------------------------
    private static void searchByPrice() {
        System.out.print("Enter minimum price: ");
        double min = scanner.nextDouble();

        System.out.print("Enter maximum price: ");
        double max = scanner.nextDouble();

        System.out.println("\n--- Products between $" + min + " and $" + max + " ---");

        boolean found = false;

        for (Product p : inventory) {
            // Check if this product's price falls inside the range
            if (p.getPrice() >= min && p.getPrice() <= max) {
                System.out.println(p);
                found = true;
            }
        }

        // Let the user know if nothing matched the range they entered
        if (!found) {
            System.out.println("No products found in that price range.");
        }
    }
}
