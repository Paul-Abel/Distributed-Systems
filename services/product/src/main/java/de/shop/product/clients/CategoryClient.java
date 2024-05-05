package de.shop.product.clients;

import de.shop.product.clients.models.Category;
import jakarta.websocket.server.PathParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "categoryClient")
public interface CategoryClient {

    @RequestMapping(method = RequestMethod.GET)
    List<Category> getCategories();
    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    Category getCategory(@PathVariable Long id);
}
