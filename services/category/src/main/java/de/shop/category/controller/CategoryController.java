package de.shop.category.controller;

import de.shop.category.clients.ProductsClient;
import de.shop.category.clients.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import de.shop.category.service.CategoryService;
import de.shop.category.model.Category;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private ProductsClient productsClient;

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping()
    public ResponseEntity<List<Category>> getCategories() {
        HttpHeaders headers = new HttpHeaders();
        String podName = System.getenv("HOSTNAME");  // Fetch the pod name from the environment variabl
        headers.add("Pod-Name", podName);

        List<Category> categories = this.categoryService.findAllCategories();
        return ResponseEntity.ok().headers(headers).body(categories);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Category>> getCategory(@PathVariable Long id) {
        String podName = System.getenv("HOSTNAME");  // Fetch the pod name from the environment variable

        HttpHeaders headers = new HttpHeaders();
        headers.add("Pod-Name", podName);

        Optional<Category> category = this.categoryService.findCategory(id);
        return ResponseEntity.ok().headers(headers).body(category);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable Long id){
        var products = productsClient.getProducts(id, null, null, null);
        for(Product product : products){
            productsClient.deleteProduct(product.getId());
        }
        this.categoryService.deleteCategory(id);

        HttpHeaders headers = new HttpHeaders();
        String podName = System.getenv("HOSTNAME");

        headers.add("Pod-Name", podName);

        return ResponseEntity.noContent().headers(headers).build();
    }

    @PostMapping()
    public ResponseEntity<Object> addCategory(@RequestBody Category category){
        this.categoryService.addCategory(category);

        HttpHeaders headers = new HttpHeaders();
        String podName = System.getenv("HOSTNAME");

        headers.add("Pod-Name", podName);

        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).build();
    }
}