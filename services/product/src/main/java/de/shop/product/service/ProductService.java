package de.shop.product.service;


import de.shop.product.clients.CategoryClient;
import de.shop.product.clients.models.Category;
import org.springframework.beans.factory.annotation.Autowired;

import de.shop.product.repository.ProductRepository;
import de.shop.product.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductService {
    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private CategoryClient categoryClient;

    public List<Product> findAll() {
            List<Product> products  =   (List<Product>) this.repository.findAll();
            for(Product product : products){
                Category category = categoryClient.getCategory(product.getCategoryId());
                product.setCategory(category);
            }
            return products;
    }

    public Optional<Product> findById(Long id) {
        // Retrieve the product from the repository
        Optional<Product> optionalProduct = repository.findById(id);

        // Check if the product is present
        if (optionalProduct.isPresent()) {
            // Get the product from Optional
            Product product = optionalProduct.get();

            // Retrieve and set the category, assuming getCategory takes a Product and returns a Category
            Category category = categoryClient.getCategory(product.getCategoryId());
            product.setCategory(category);

            // Return the product as Optional
            return Optional.of(product);
        }

        // Return empty Optional if product was not found
        return Optional.empty();
    }

    public List<Product> findByCategory(Long categoryId) {
        return (List<Product>) this.repository.findByCategory(categoryId);
    }

    public List<Product> findBySearchCriteria(String search, Double minimumPrice, Double maximumPrice) {
        if (search != null && minimumPrice != null && maximumPrice != null) {
            System.out.println("findBySearchCriteria");
            return (List<Product>) this.repository.findBySearchCriteria("%" + search + "%", minimumPrice, maximumPrice);
        } else if (search != null && minimumPrice != null) {
            System.out.println("findByKeywordAndMinPrice");
            return (List<Product>) this.repository.findByKeywordAndMinPrice("%" + search + "%", minimumPrice);
        } else if (search != null && maximumPrice != null) {
            System.out.println("findByKeywordAndMaxPrice");
            return (List<Product>) this.repository.findByKeywordAndMaxPrice("%" + search + "%", maximumPrice);
        } else if (search == null && minimumPrice != null) {
            System.out.println("findByMinPrice");
            return (List<Product>) this.repository.findByMinPrice(minimumPrice);
        } else if (search == null && maximumPrice != null) {
            System.out.println("findByMaxPrice");
            return (List<Product>) this.repository.findByMaxPrice(maximumPrice);
        }

        return (List<Product>) this.repository.findByKeyword("%" + search + "%");
    }

    public void add(Product product) {
        this.repository.save(product);
    }

    public void delete(Long id) {
        this.repository.deleteById(id);
    }

}