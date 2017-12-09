package com.sg.shop.service;

import java.util.List;
import java.util.Map;

import com.sg.shop.model.Product;

/**
 * DiscountService calculates all the applicable discounts for the customer
 */

public interface DiscountService {

	Map<Integer, Integer> getDiscount(List<Product> products);
}
