package de.shop.product.model;

import de.shop.product.clients.models.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull
    private Long id;

    @NonNull
    private String name;

    private String details;

    private Long categoryId;

    private double price;

    @Transient
    private Category category;

    public Product() {
    }

    public Product(String name, double price, Long categoryId) {
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
    }

    public Product(String name, double price, String details, Long categoryId) {
        this.name = name;
        this.price = price;
        this.details = details;
        this.categoryId = categoryId;
    }
}