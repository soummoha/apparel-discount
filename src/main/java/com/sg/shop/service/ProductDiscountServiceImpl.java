package com.sg.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sg.shop.exception.DiscountServiceException;
import com.sg.shop.model.Product;

@Service("productDiscountService")
public class ProductDiscountServiceImpl implements DiscountService {

	@Autowired
	@Qualifier("categoryDiscountService")
	private DiscountService categoryDiscountService;

	@Autowired
	@Qualifier("brandDiscountService")
	private DiscountService brandDiscountService;

	@Override
	public Map<Integer, Integer> getDiscount(List<Product> products) {
		
		if( products == null || products.isEmpty()){
			throw new DiscountServiceException("product list is either null or empty");
		}

		/*
		 * Brand discount map holds product id as key and brand discount as
		 * value
		 */
		Map<Integer, Integer> brandDiscountMap = brandDiscountService.getDiscount(products);

		/*
		 * Category discount map holds product id as key and category discount
		 * as value
		 */
		Map<Integer, Integer> categoryDiscountMap = categoryDiscountService.getDiscount(products);

		/*
		 * Product discount map holds product id as key and total product
		 * discount as value
		 */
		Map<Integer, Integer> productDiscountMap = new HashMap<>();

		products.stream()
			.mapToInt(Product::getProductId)
			.forEach(id -> {
				int totalDiscount = Math.max(brandDiscountMap.get(id), categoryDiscountMap.get(id));
				productDiscountMap.put(id, totalDiscount);
			});

		return productDiscountMap;

	}

}
