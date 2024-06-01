package de.shop.product.controller;

import de.shop.product.clients.CategoryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import de.shop.product.service.ProductService;
import de.shop.product.model.Product;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService service;

    @Autowired
    private CategoryClient categoryClient;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String search,
            @RequestParam(required = false, defaultValue = "0.0") Double minimumPrice,
            @RequestParam(required = false) Double maximumPrice
    ) {
        if (categoryId == null) {
            return new ResponseEntity<>(this.service.findBySearchCriteria(search, minimumPrice, maximumPrice), HttpStatus.OK);
        }

        return new ResponseEntity<>(this.service.findByCategory(categoryId), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getProducts(
    ) {
        return new ResponseEntity<>(this.service.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Product>> getProduct(@PathVariable Long id) {
        return new ResponseEntity<>(this.service.findById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Object> addProduct(@RequestBody Product product){
        if (product.getCategoryId() == null || product.getName() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category ID and name cannot be null");
        }
        var category = categoryClient.getCategory(product.getCategoryId());
        if (category == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category ID not known");
        }

        this.service.add(product);
        return new ResponseEntity<>(this.service.findById(product.getId()), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id){
        this.service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}