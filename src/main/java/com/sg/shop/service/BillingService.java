package com.sg.shop.service;

import java.util.List;

import com.sg.shop.model.Product;

/**
 * Billing service generates the bill for the products selected by customer.
 *
 */
public interface BillingService {

	int makeBill(List<Product> products);

}
