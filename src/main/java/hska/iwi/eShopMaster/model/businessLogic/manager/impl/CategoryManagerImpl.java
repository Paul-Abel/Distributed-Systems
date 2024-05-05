package hska.iwi.eShopMaster.model.businessLogic.manager.impl;


import hska.iwi.eShopMaster.connection.CategoryConnection;
import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.database.dataobjects.Category;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryManagerImpl implements CategoryManager {

    private final CategoryConnection categoryConnection = Feign.builder().encoder(new GsonEncoder()).decoder(new GsonDecoder()).target(CategoryConnection.class, "http://category:8080/api/v1/category");

    public CategoryManagerImpl() {
    }

    public List<Category> getCategories() {
        return categoryConnection.getCategories();
    }

    public Category getCategory(int id) {
        return categoryConnection.getCategory((long) id);
    }


    // no usage
    public Category getCategoryByName(String name) {
        return null;
    }

    public void addCategory(String name) {
        Map<String, Object> request = new HashMap<String, Object>();
        request.put("name", name);
        categoryConnection.createCategory(request);
    }

    public void delCategory(Category cat) {
        int id = cat.getId();
// 		Products are also deleted because of relation in Category.java 
        delCategoryById(id);
    }

    public void delCategoryById(int id) {
        categoryConnection.deleteCategory((long) id);
    }
}
