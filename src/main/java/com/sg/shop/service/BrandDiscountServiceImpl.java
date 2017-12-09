package com.sg.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.shop.configuration.BrandConfiguration;
import com.sg.shop.model.Product;

@Service("brandDiscountService")
public class BrandDiscountServiceImpl implements DiscountService {

	@Autowired
	private BrandConfiguration brandConfig;

	@Override
	public Map<Integer, Integer> getDiscount(List<Product> products) {

		/*
		 * Brand discount map holds brand name as key and discount as value
		 */
		Map<String, Integer> brandDiscountMap = brandConfig.getBrandDiscountMap();

		/*
		 * Product discount map holds product id as key and discount as value
		 */
		Map<Integer, Integer> productDiscountMap = new HashMap<>();

		products.forEach(p -> {
			productDiscountMap.put(p.getProductId(), brandDiscountMap.get(p.getBrand()));
		});

		return productDiscountMap;

	}

}
