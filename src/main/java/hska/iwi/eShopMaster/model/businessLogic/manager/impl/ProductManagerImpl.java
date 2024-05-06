package hska.iwi.eShopMaster.model.businessLogic.manager.impl;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import hska.iwi.eShopMaster.connection.ProductConnection;

import hska.iwi.eShopMaster.model.businessLogic.manager.ProductManager;
import hska.iwi.eShopMaster.model.database.dataAccessObjects.ProductDAO;
import hska.iwi.eShopMaster.model.database.dataobjects.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductManagerImpl implements ProductManager {

	private final ProductConnection productConnection = Feign.builder()
			.encoder(new GsonEncoder())
			.decoder(new GsonDecoder())
			.target(ProductConnection.class, "http://product:8081/api/v1/product");


	public ProductManagerImpl() {}

	public List<Product> getProducts() {
		return productConnection.getProducts();
	}
	
	public List<Product> getProductsForSearchValues(String searchDescription,
			Double searchMinPrice, Double searchMaxPrice) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		if (searchDescription != null) {
			queryMap.put("search", searchDescription);
		}

		if (searchMinPrice != null) {
			queryMap.put("minimumPrice", searchMinPrice);
		}

		if (searchMaxPrice != null) {
			queryMap.put("maximumPrice", searchMaxPrice);
		}

		return productConnection.search(queryMap);
	}

	public Product getProductById(int id) {
		return productConnection.getProduct((long) id);
	}

	public Product getProductByName(String name) {
		return null;
	}
	
	public int addProduct(String name, double price, int categoryId, String details) {
		Map<String, Object> request = new HashMap<String, Object>();

		request.put("name", name);
		request.put("price", price);
		request.put("categoryId", categoryId);
		request.put("details", details);

		return productConnection.createProduct(request).getId();
	}
	

	public void deleteProductById(int id) {
		productConnection.deleteProduct((long) id);
	}

	public boolean deleteProductsByCategoryId(int categoryId) {
		// TODO Auto-generated method stub
		return false;
	}

}
