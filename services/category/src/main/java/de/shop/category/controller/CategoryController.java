package de.shop.category.controller;

import de.shop.category.clients.ProductsClient;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/all")
    public ResponseEntity<List<Category>> getCategories(){
        var categoryList = this.categoryService.findAllCategories();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Optional<Category>> getCategory(@PathVariable Long id){
        var category = this.categoryService.findCategory(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<Object> deleteCategory(@PathVariable Long id){
        var products = productsClient.getProducts(id, null, null, null);
        if (products.size() > 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("A category can only be deleted if there aren't any products linked to it.");
        }
        this.categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping()
    public ResponseEntity<Object> addCategory(@RequestBody Category category){
        this.categoryService.addCategory(category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}