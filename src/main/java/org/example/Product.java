package org.example;

public class Product {
    private int productId;
    private String productName;
    private String category;
    private double sellingPrice;
    private int availableQuantity;

    public Product(int productId, String productName, double buyingPrice, int availableQuantity, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.sellingPrice = (buyingPrice * 0.5) + buyingPrice;
        this.availableQuantity = availableQuantity;
    }

    public int getProductId() {
        return productId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    @Override
    public String toString() {
        return "Product ID: " + productId +
                ", Name: " + productName +
                ", Selling Price: " + sellingPrice +
                ", Available Quantity: " + availableQuantity +
                ", Category: " + category;
    }
}
