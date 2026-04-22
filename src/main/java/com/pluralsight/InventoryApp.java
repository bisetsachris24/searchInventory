package com.pluralsight;

import java.util.*;

public class InventoryApp {

    // creating a scanner
    static Scanner scanner = new Scanner(System.in);
    static int inventory = getInventory();


    // create main method that java will run
    public static void main(String[] args) {

// declaring int named choice to let user input between 1 to 5
        int choice;
// a do while loop will always run its body at least once before asking
        do {

            // making print oout print out statements
            System.out.println("\nWhat do you want to do?");
            System.out.println("1- List all products");
            System.out.println("2- Lookup a product by its id");
            System.out.println("3- Find products within a price range");
            System.out.println("4- Add a new product");
            System.out.println("5- Quit");
            System.out.print("Enter command: ");

// the scanner  will store the user integer number of their choice
            choice = scanner.nextInt();

            switch (choice) {
                // call method to prints all products
                case 1:
                    listProducts();
                    break; // jumps out of the witch so we don't fall into case 2
                case 2: // another call method that print product by Id
                    lookupById();
                    break;
                case 3: // call method for product by price
                    searchByPrice();
                    break;
                case 4: // call method that add new product
                    addProduct();
                    break;
                case 5: // goodbye meesage and it will stop loop
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }

        } while (choice != 5);// 5 is set  at to quit if a user input 5 it's gonna stop
    }

    private static int getInventory() {
        return 0;
    }

    private static void listProducts() {
    }

    private static void lookupById() {
    }

    private static void addProduct() {
    }

    private static void searchByPrice() {
    }
}