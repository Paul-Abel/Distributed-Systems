package de.shop.category.service;

import de.shop.category.model.Category;
import de.shop.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {


    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAllCategories(){
        return (List<Category>)this.categoryRepository.findAll();
    }

    public Optional<Category> findCategory(Long id){
        return this.categoryRepository.findById(id);
    }

    public void addCategory(Category category){
        this.categoryRepository.save(category);
    }

    public void deleteCategory(Long id){
        this.categoryRepository.deleteById(id);
    }
}