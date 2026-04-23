package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class InventoryApp {

    // i create scanner to ask user for input
    static Scanner theScanner = new Scanner(System.in);
    // create array list
    static ArrayList<Product> myInventory = getInventory();

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
                    // print every product in the list
                    listProducts();
                    break;
                // find one product by its ID number
                case 2:
                    lookupById();
                    break;

                case 3:
                    // find products inside a price range
                    searchByPrice();
                    break;
                case 4:
                    // add a brand-new product to the list
                    addProduct();
                    break;
                // loop stops because choice == 5
                case 5:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please enter 1 - 5.");
            }
// keep looping until the user picks 5 to quit
        } while (choice != 5);
    }

    private static ArrayList<Product> getInventory() {

        ArrayList<Product> products = new ArrayList<>();

        // Looking the exact folder Java is looking in
        System.out.println("Looking for file at: " + new java.io.File("main/src//reinventory.csv").getAbsolutePath());

        try {
            FileReader amaniFilereader = new FileReader("src/main/resources/inventory.csv");
            BufferedReader amaniReader = new BufferedReader(amaniFilereader);

            String line;
            while ((line = amaniReader.readLine()) != null) {

                String[] parts = line.split("\\|");

                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);

                products.add(new Product(id, name, price));
            }

            amaniReader.close();

        } catch (Exception e) {
            System.out.println("Error reading inventory.csv: " + e.getMessage());
        }

        return products;
    }


    private static void listProducts() {
        System.out.println("\n--- Product List ---");

        // Sort alphabetically by name before printing
        Collections.sort(myInventory);

        for (Product p : myInventory) {
            System.out.println(p);  // calls Product's toString() method
        }
    }

    private static void lookupById() {
        // Ask the user to type the ID they want to search for
        System.out.print("Enter product ID to search: ");
        int id = theScanner.nextInt();

        // Loop through every product in the inventory list
        for (Product p : myInventory) {

            // Check if this product's ID matches what the user typed
            if (p.getId() == id) {
                System.out.println("Found: " + p);
                return; // stop the loop immediately once we find a match
            }
        }

        // This line only runs if the loop finished without finding a match
        System.out.println("No product found with ID: " + id);
    }

    private static void addProduct() {
        // Ask the user for the new product's ID number
        System.out.print("Enter new product ID: ");
        int id = theScanner.nextInt();

        // Ask for the product name
        // nextLine() is used instead of next() so it can handle names with spaces
        // The first nextLine() clears the leftover Enter key from nextInt() above
        theScanner.nextLine();
        System.out.print("Enter product name: ");
        String name = theScanner.nextLine();

        // Ask for the price
        System.out.print("Enter product price: ");
        double price = theScanner.nextDouble();

        // Create a brand new Product object using the three values the user typed
        Product newProduct = new Product(id, name, price);

        // Add the new product to the inventory ArrayList
        myInventory.add(newProduct);

        // Confirm to the user that it worked
        System.out.println("Product added successfully: " + newProduct);
    }
    private static void searchByPrice() {
        // Ask the user for the lowest price they want to search from
        System.out.print("Enter minimum price: ");
        double min = theScanner.nextDouble();

        // Ask the user for the highest price they want to search to
        System.out.print("Enter maximum price: ");
        double max = theScanner.nextDouble();

        // Print a header showing the range the user entered
        System.out.println("\n--- Products between $" + min + " and $" + max + " ---");

        // This flag tracks whether we found at least one match
        boolean found = false;

        // Loop through every product in the inventory
        for (Product p : myInventory) {

            // Check if this product's price falls inside the min and max range
            // >= means "greater than or equal to" and <= means "less than or equal to"
            if (p.getPrice() >= min && p.getPrice() <= max) {
                System.out.println(p);  // print the matching product
                found = true;           // mark that we found at least one match
            }
        }

        // If the loop finished and found is still false, no products matched
        if (!found) {
            System.out.println("No products found in that price range.");
        }
    }
}



