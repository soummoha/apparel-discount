package com.sg.shop.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sg.shop.exception.BillingServiceException;
import com.sg.shop.model.Product;

@Service("billingServiceImpl")
public class BillingServiceImpl implements BillingService {

	@Autowired
	@Qualifier("productDiscountService")
	private DiscountService productDiscountService;

	@Override
	public int makeBill(List<Product> products) {
		
		if( products == null || products.isEmpty()){
			throw new BillingServiceException("product list is either null or empty");
		}

		Map<Integer, Integer> productDiscountMap = productDiscountService.getDiscount(products);

		int totalProductPrice = 0;

		for (Product p : products) {			
			int productBasePrice = p.getPrice();
			int productDiscountPercentage = productDiscountMap.get(p.getProductId());
			float percent = (float)(100 - productDiscountPercentage) / (float)100;
			int productPrice = (int)(productBasePrice * percent);
			totalProductPrice += productPrice;			
		}

		return totalProductPrice;

	}

}
