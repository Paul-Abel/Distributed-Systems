package de.shop.product.repository;

import de.shop.product.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    // Find Product with Category id
    @Query("from Product p where p.categoryId=:categoryId")
    Iterable<Product> findByCategory(Long categoryId);

    // Find Product with further info
    @Query("from Product p where (p.name LIKE :keyword OR p.details LIKE :keyword) AND p.price BETWEEN :minPrice AND :maxPrice")
    Iterable<Product> findBySearchCriteria(String keyword, double minPrice, double maxPrice);

}
