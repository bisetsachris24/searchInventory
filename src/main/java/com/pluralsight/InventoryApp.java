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

        ArrayList<Product> products = new ArrayList<>();

        // ADD THIS LINE — it will print the exact folder Java is looking in
        System.out.println("Looking for file at: " + new java.io.File("main/src//reinventory.csv").getAbsolutePath());

        try {
            FileReader amaniFilereader = new FileReader("src/main/resources/inventory.csv");
            BufferedReader reader = new BufferedReader(amaniFilereader);

            String line;
            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("\\|");

                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);

                products.add(new Product(id, name, price));
            }

            reader.close();

        } catch (Exception e) {
            System.out.println("Error reading inventory.csv: " + e.getMessage());
        }

        return products;
    }


    private static void listProducts() {
        System.out.println("\n--- Product List ---");

        // Sort alphabetically by name before printing
        Collections.sort(inventory);

        for (Product p : inventory) {
            System.out.println(p);  // calls Product's toString() method
        }
    }



    private static void lookupById() {
        // Ask the user to type the ID they want to search for
        System.out.print("Enter product ID to search: ");
        int id = theScanner.nextInt();

        // Loop through every product in the inventory list
        for (Product p : inventory) {

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
        inventory.add(newProduct);

        // Confirm to the user that it worked
        System.out.println("Product added successfully: " + newProduct);
    }



