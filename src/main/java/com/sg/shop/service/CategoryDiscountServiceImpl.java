package com.sg.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.shop.configuration.CategoryConfiguration;
import com.sg.shop.model.Product;

@Service("categoryDiscountService")
public class CategoryDiscountServiceImpl implements DiscountService {

	@Autowired
	private CategoryConfiguration categoryConfig;

	@Override
	public Map<Integer, Integer> getDiscount(List<Product> products) {
		/*
		 * Category discount map contains category name as key and discount as value
		 */
		Map<String, Integer> categoryDiscountMap = categoryConfig.getCategoryDiscountMap();

		/*
		 * Product discount map holds product id as key and discount as value
		 */
		Map<Integer, Integer> productDiscountMap = new HashMap<>();

		products.forEach(p -> {
			productDiscountMap.put(p.getProductId(), categoryDiscountMap.get(p.getCategory()));
		});

		return productDiscountMap;

	}

}
