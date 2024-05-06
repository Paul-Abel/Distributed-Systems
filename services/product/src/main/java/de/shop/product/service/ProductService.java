package de.shop.product.service;


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

    public List<Product> findAll() {
        return (List<Product>) this.repository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return this.repository.findById(id);
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