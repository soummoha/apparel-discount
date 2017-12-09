package com.sg.shop.repository;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sg.shop.model.Product;

@Repository("inventoryRepositoryImpl")
public class InventoryRepositoryImpl implements InventoryRepository {

	private List<Product> allProducts;

	@Override
	public boolean containsAll(List<Integer> productIds) {
		List<Integer> inventoryProdIds = allProducts.stream()
				.mapToInt(Product::getProductId)
				.boxed().collect(toList());
		return inventoryProdIds.containsAll(productIds);
	}

	@Override
	public List<Product> getAllProducts() {
		return allProducts;
	}

	@Override
	public List<Product> getProducts(List<Integer> productIds) {
		return allProducts.stream()
				.filter(p -> productIds.contains(p.getProductId()))
				.collect(toList());
	}

	@Override
	public void setAllProducts(List<Product> allProducts) {
		this.allProducts = allProducts;
	}

}
