package com.sg.shop.repository;

import java.util.List;

import com.sg.shop.model.Product;

/**
 * InventoryRepository class provides functionality to access product inventory
 *
 */
public interface InventoryRepository {

	boolean containsAll(List<Integer> productIds);

	List<Product> getAllProducts();

	List<Product> getProducts(List<Integer> productIds);

	void setAllProducts(List<Product> productIds);

}
