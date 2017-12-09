package com.sg.shop.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sg.shop.exception.BillingServiceException;
import com.sg.shop.model.Product;
import com.sg.shop.repository.InventoryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ProductBillingServiceTest {

	@Autowired
	@Qualifier("billingServiceImpl")
	private BillingService billingService;

	@Autowired
	@Qualifier("inventoryRepositoryTestImpl")
	private InventoryRepository inventoryRepository;

	private List<Product> products;

	@Before
	public void setUp() {
		inventoryRepository.setAllProducts(null);
		List<Integer> productIds = new ArrayList<>();
		productIds.add(1);
		productIds.add(2);
		productIds.add(3);
		productIds.add(4);
		products = inventoryRepository.getProducts(productIds);
	}

	@Test
	public void testMakeBillOnAllInventory() {
		assertEquals(5360, billingService.makeBill(inventoryRepository.getAllProducts()));
	}

	@Test
	public void testMakeBillOnSelectedProducts() {
		assertEquals(3860, billingService.makeBill(products));
	}

	@Test(expected = BillingServiceException.class)
	public void testMakeBillForNullProductList() {
		billingService.makeBill(null);
	}
	
	@Test(expected = BillingServiceException.class)
	public void testMakeBillForEmptyProductList() {
		List<Product> products = new ArrayList<>();
		billingService.makeBill(products);
	}

}
