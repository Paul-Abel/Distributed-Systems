package de.shop.category.clients.models;

public class Product {

    private Long id;

    private String name;

    private double price;

    private String details;

    private Long categoryId;

    public Product() {
    }

    public Product(String name, double price, String details, Long categoryId) {
        this.name = name;
        this.price = price;
        this.details = details;
        this.categoryId = categoryId;
    }

    public Product(String name, double price, Long categoryId) {
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDetails() {
        return details;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Long getId() {
        return id;
    }
}
