package com.sg.shop.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sg.shop.model.Product;

@Repository("inventoryRepositoryTestImpl")
public class InventoryRepositoryTestImpl extends InventoryRepositoryImpl {

	@Override
	public void setAllProducts(List<Product> productIds) {
		List<Product> allProducts = new ArrayList<>();
		allProducts.add(new Product(1, "Arrow", "Shirts", 800));
		allProducts.add(new Product(2, "Vero Moda", "Dresses", 1400));
		allProducts.add(new Product(3, "Provogue", "Footwear", 1800));
		allProducts.add(new Product(4, "Wrangler", "Jeans", 2200));
		allProducts.add(new Product(5, "UCB", "Shirts", 1500));
		super.setAllProducts(allProducts);
	}

}
