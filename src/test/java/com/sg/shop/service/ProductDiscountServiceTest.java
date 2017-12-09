package com.sg.shop.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sg.shop.exception.DiscountServiceException;
import com.sg.shop.model.Product;
import com.sg.shop.repository.InventoryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ProductDiscountServiceTest {

	@Autowired
	@Qualifier("productDiscountService")
	private DiscountService discountService;

	@Autowired
	@Qualifier("inventoryRepositoryTestImpl")
	private InventoryRepository inventoryRepository;

	@Before
	public void setUp() {
		inventoryRepository.setAllProducts(null);
	}

	@Test
	public void testGetDiscountOnInventory() {
		List<Product> products = inventoryRepository.getAllProducts();
		Map<Integer, Integer> productDiscounts = discountService.getDiscount(products);
		assertEquals(5, products.size());
		assertEquals(20, productDiscounts.get(1).intValue());
		assertEquals(60, productDiscounts.get(2).intValue());
		assertEquals(50, productDiscounts.get(3).intValue());
		assertEquals(20, productDiscounts.get(4).intValue());
		assertEquals(0, productDiscounts.get(5).intValue());

	}

	@Test
	public void testGetDiscountOnSpecificProducts() {
		List<Integer> productIds = new ArrayList<>();
		productIds.add(1);
		productIds.add(2);
		productIds.add(3);
		productIds.add(4);
		List<Product> products = inventoryRepository.getProducts(productIds);
		Map<Integer, Integer> productDiscounts = discountService.getDiscount(products);
		assertEquals(4, products.size());
		assertEquals(20, productDiscounts.get(1).intValue());
		assertEquals(60, productDiscounts.get(2).intValue());
		assertEquals(50, productDiscounts.get(3).intValue());
		assertEquals(20, productDiscounts.get(4).intValue());
	}
	
	@Test(expected = DiscountServiceException.class)
	public void testMakeBillForNullProductList() {
		discountService.getDiscount(null);
	}
	
	@Test(expected = DiscountServiceException.class)
	public void testMakeBillForEmptyProductList() {
		List<Product> products = new ArrayList<>();
		discountService.getDiscount(products);
	}


}
