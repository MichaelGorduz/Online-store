package org.example;

import java.util.*;

class StoreInformation {
    private List<User> users;
    private Map<Integer, Product> products;
    private User loggedInUser;

    public StoreInformation() {
        users = new ArrayList<>();
        products = new HashMap<>();
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addProduct(Product product) {
        products.put(product.getProductId(), product);
    }

    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                loggedInUser = user;
                return user;
            }
        }
        loggedInUser = null;
        return null;
    }

    public List<Product> listProducts() {
        return new ArrayList<>(products.values());
    }

    public void registerNewCustomer(String username, String email, String password) {
        if (username == null || username.isEmpty() || email == null || email.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidInputException("Invalid input for registration");
        }

        // Automatically set role to "customer" for new registered customers
        User newUser = new User(username, email, password, 100, "customer");
        users.add(newUser);
    }


    public Product searchProductById(int productId) {
        return products.get(productId);
    }

    public Product searchProductByName(String name) {
        Optional<Product> productOptional = products.values().stream()
                .filter(product -> product.getProductName().equalsIgnoreCase(name))
                .findFirst();

        return productOptional.orElse(null);
    }

    public List<Product> listProductsByCategory(String category) {
        List<Product> productsInCategory = new ArrayList<>();
        for (Product product : products.values()) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                productsInCategory.add(product);
            }
        }
        return productsInCategory;
    }

    public List<Product> listProductsByPrice() {
        List<Product> productsByPrice = new ArrayList<>(products.values());

        productsByPrice.sort(Comparator.comparing(Product::getSellingPrice));

        return productsByPrice;
    }

    public double calculateTotalAmountSpent() {
        double totalAmount = 0.0;

        for (Product product : products.values()) {
            totalAmount += product.getSellingPrice() * product.getAvailableQuantity();
        }
        return totalAmount;
    }

    public double calculateProfitByCategory(String category) {
        double profitByCategory = 0.0;

        for (Product product : products.values()) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                profitByCategory += product.getSellingPrice() * product.getAvailableQuantity();
            }
        }
        return profitByCategory;
    }

}