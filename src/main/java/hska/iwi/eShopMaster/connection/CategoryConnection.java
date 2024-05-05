package hska.iwi.eShopMaster.connection;


import feign.Param;
import feign.RequestLine;
import hska.iwi.eShopMaster.model.database.dataobjects.Category;

import java.util.List;
import java.util.Map;

public interface CategoryConnection {
    @RequestLine("DELETE /{id}")
    void deleteCategory(@Param("id") Long id);
    @RequestLine("POST")
    void createCategory(Map<String, Object> requestBody);
    @RequestLine("GET")
    List<Category> getCategories();

    @RequestLine("GET /{id}")
    Category getCategory(@Param("id") Long id);




}


