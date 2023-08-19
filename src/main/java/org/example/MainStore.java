package org.example;

import java.util.*;

public class MainStore {
    public static void main(String[] args) {
        StoreInformation store = new StoreInformation();
        Scanner sc = new Scanner(System.in);

        User test = new User("test", "test@yahoo.com", "test", 100, "customer");
        User owner = new User("princy", "princy@yahoo.com", "admin", 1000000, "admin");
        store.addUser(test);
        store.addUser(owner);

        List<Product> productsToAdd = Arrays.asList(
                new Product(1, "Tomatoes", 5.0, 100, "Vegetables"),
                new Product(2, "Aquafresh toothpaste", 10.0, 50, "Toiletry"),
                new Product(3, "Rye bread", 3.0, 120, "Grocery"),
                new Product(4, "Pen", 1.0, 200, "Stationery")
        );

        productsToAdd.forEach(store::addProduct);

        while (true) {
            System.out.println("Please note: To check program functionality, there is stored Owner role as 'princy, admin', and Customer role as 'test, test'");
            System.out.println("1: Login existing customer");
            System.out.println("2: Register new customer");
            System.out.println("3: Exit");
            System.out.print("-> ");

            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    System.out.print("Enter username: ");
                    String loginUsername = sc.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = sc.nextLine();

                    User loggedInUser = store.login(loginUsername, loginPassword);

                    if (loggedInUser != null) {
                        System.out.println("Login successful!");
                        int superCoins = store.getLoggedInUser().getSuperCoins();
                        System.out.println("Available super coins: " + superCoins);

                        // Adding thread here
                        Thread userThread = new Thread(() -> {
                            if (loggedInUser.getRole().equalsIgnoreCase("admin")) {
                                showAdminMenu(store, sc);
                            } else {
                                showCustomerMenu(store, sc);
                            }
                        });

                        userThread.start();
                        try {
                            userThread.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("Login failed. Invalid credentials.");
                    }
                    break;

                case "2":
                    System.out.print("Enter username: ");
                    String newUsername = sc.nextLine();
                    System.out.print("Enter email: ");
                    String newEmail = sc.nextLine();
                    System.out.print("Enter password: ");
                    String newPassword = sc.nextLine();
                    store.registerNewCustomer(newUsername, newEmail, newPassword);
                    System.out.println("Registration successful! You have 100 Super coins as bonus!");

                    // Log in the newly registered user
                    User newUser = store.login(newUsername, newPassword);
                    if (newUser != null) {
                        int superCoins = newUser.getSuperCoins();
                        System.out.println("Available super coins: " + superCoins);

                        // Show the customer menu for the newly registered user
                        showCustomerMenu(store, sc);
                    } else {
                        System.out.println("Login failed. Please log in to access the customer menu.");
                    }
                    break;

                case "3":
                    System.out.println("Exiting the Store.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void showAdminMenu(StoreInformation store, Scanner sc) {
        while (true) {
            System.out.println("Admin Menu:");
            System.out.println("1: Show all products");
            System.out.println("2: Search product by ID");
            System.out.println("3: Search product by Name");
            System.out.println("4: Show product by Category");
            System.out.println("5: Calculate total amount spent");
            System.out.println("6: Calculate profit by category");
            System.out.println("7: exit");
            System.out.print("-> ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    List<Product> allProducts = store.listProducts();
                    if (!allProducts.isEmpty()) {
                        allProducts.forEach(product -> {
                            System.out.println("Product ID: " + product.getProductId());
                            System.out.println("Product Name: " + product.getProductName());
                            System.out.println("Category: " + product.getCategory());
                            System.out.println("Selling Price: " + product.getSellingPrice());
                            System.out.println(); // Empty line between products
                        });
                    } else {
                        System.out.println("No products available.");
                    }
                    break;

                case "2":
                    System.out.print("Enter product ID: ");
                    int productId = Integer.parseInt(sc.nextLine());
                    Product searchedProductById = store.searchProductById(productId);
                    if (searchedProductById != null) {
                        System.out.println(searchedProductById);
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;

                case "3":
                    System.out.print("Enter product Name: ");
                    String productName = sc.nextLine();
                    Product searchedProductByName = store.searchProductByName(productName);
                    if (searchedProductByName != null) {
                        System.out.println(searchedProductByName);
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;

                case "4":
                    System.out.print("Enter product Category: ");
                    String category = sc.nextLine();
                    List<Product> productsByCategory = store.listProductsByCategory(category);
                    if (!productsByCategory.isEmpty()) {
                        for (Product product : productsByCategory) {
                            System.out.println(product);
                        }
                    } else {
                        System.out.println("No products found in the specified category.");
                    }
                    break;

                case "5":
                    double totalSpent = store.calculateTotalAmountSpent();
                    System.out.println("Total amount spent: " + totalSpent);
                    break;

                case "6":
                    System.out.print("Enter Product Category: ");
                    String calcByCat = sc.nextLine();
                    double profitForCategory = store.calculateProfitByCategory(calcByCat);
                    System.out.println("Profit for category " + calcByCat + ": " + profitForCategory);
                    break;

                case "7":
                    System.out.println("Exiting the application.");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void showCustomerMenu(StoreInformation store, Scanner sc) {
        while (true) {
            System.out.println("Customer Menu:");
            System.out.println("1: Show all products");
            System.out.println("2: Filter products by category");
            System.out.println("3: Filter products by price (low to high)");
            System.out.println("4: Exit");

            System.out.print("-> ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    List<Product> allProducts = store.listProducts();
                    if (!allProducts.isEmpty()) {
                        allProducts.forEach(product -> {
                            System.out.println("Product ID: " + product.getProductId());
                            System.out.println("Product Name: " + product.getProductName());
                            System.out.println("Category: " + product.getCategory());
                            System.out.println("Price: " + product.getSellingPrice());
                            System.out.println(); // Empty line between products
                        });
                    } else {
                        System.out.println("No products available.");
                    }
                    break;

                case "2":
                    System.out.print("Enter product Category: ");
                    String category = sc.nextLine();
                    List<Product> productsByCategory = store.listProductsByCategory(category);
                    if (!productsByCategory.isEmpty()) {
                        for (Product product : productsByCategory) {
                            System.out.println(product);
                        }
                    } else {
                        System.out.println("No products found in the specified category.");
                    }
                    break;

                case "3":
                    List<Product> productsByPrice = store.listProductsByPrice();
                    if (!productsByPrice.isEmpty()) {
                        for (Product product : productsByPrice) {
                            System.out.println("Product ID: " + product.getProductId());
                            System.out.println("Product Name: " + product.getProductName());
                            System.out.println("Category: " + product.getCategory());
                            System.out.println("Price: " + product.getSellingPrice());
                            System.out.println(); // Empty line between products
                        }
                    } else {
                        System.out.println("No products found.");
                    }
                    break;

                case "4":
                    System.out.println("Exiting the customer menu.");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

}