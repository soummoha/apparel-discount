package com.sg.shop.configuration;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sg.shop.model.Brand;

import static java.util.stream.Collectors.toList;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class BrandConfigurationTest {

	@Autowired
	private BrandConfiguration brandConfig;

	@Test
	public void testGetBrandDiscountMap() {
		Map<String, Integer> brandDiscountMap = brandConfig.getBrandDiscountMap();

		assertEquals(20, brandDiscountMap.get("Arrow").intValue());
		assertEquals(20, brandDiscountMap.get("Provogue").intValue());
		assertEquals(0, brandDiscountMap.get("UCB").intValue());
		assertEquals(60, brandDiscountMap.get("Vero Moda").intValue());
		assertEquals(10, brandDiscountMap.get("Wrangler").intValue());
		assertEquals(5, brandDiscountMap.get("Adidas").intValue());
	}

	@Test
	public void testGetBrands() {
		List<String> brands = brandConfig.getBrands().stream()
				.map(Brand::getName).collect(toList());

		assertTrue(brands.contains("Arrow"));
		assertTrue(brands.contains("Provogue"));
		assertTrue(brands.contains("UCB"));
		assertTrue(brands.contains("Vero Moda"));
		assertTrue(brands.contains("Wrangler"));
		assertTrue(brands.contains("Adidas"));
	}

}
