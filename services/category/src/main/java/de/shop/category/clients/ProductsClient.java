package de.shop.category.clients;

import de.shop.category.clients.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "productsClient")
public interface ProductsClient {

    @RequestMapping(method = RequestMethod.GET, value="search")
    List<Product> getProducts(@RequestParam(required = false) Long categoryId,
                              @RequestParam(required = false) String search,
                              @RequestParam(required = false) Double minimumPrice,
                              @RequestParam(required = false) Double maximumPrice);
}
