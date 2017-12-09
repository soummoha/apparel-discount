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

import com.sg.shop.model.Product;
import com.sg.shop.repository.InventoryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class BrandDiscountServiceImplTest {

	@Autowired
	@Qualifier("brandDiscountService")
	private DiscountService brandDiscountService;
	
	@Autowired
	@Qualifier("inventoryRepositoryTestImpl")
	private InventoryRepository inventoryRepository;
	
	@Before
	public void setUp(){
		inventoryRepository.setAllProducts(null);
	}

	@Test
	public void testGetDiscount() {
		List<Integer> productIds = new ArrayList<>();
		productIds.add(1);
		productIds.add(2);
		productIds.add(3);
		productIds.add(4);
		
		List<Product> productList = inventoryRepository.getProducts(productIds);
		
		Map<Integer, Integer> brandDiscountMap = brandDiscountService.getDiscount(productList);
		
		assertEquals(20, brandDiscountMap.get(1).intValue());
		assertEquals(60, brandDiscountMap.get(2).intValue());
		assertEquals(20, brandDiscountMap.get(3).intValue());
		assertEquals(10, brandDiscountMap.get(4).intValue());
	}

}
