package com.sg.shop.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.sg.shop.model.Brand;

/**
 * The configuration class reads all the product brand configurations from
 * <file>appliacation.yml<file>
 *
 */

@ConfigurationProperties(prefix = "product")
@Service
public class BrandConfiguration {

	private Map<String, Integer> brandDiscountMap = new HashMap<>();

	private List<Brand> brands = new ArrayList<>();

	public Map<String, Integer> getBrandDiscountMap() {
		return brandDiscountMap;
	}

	public List<Brand> getBrands() {
		return brands;
	}

	@PostConstruct
	public void postConstruct() {
		for (Brand brand : brands) {
			brandDiscountMap.put(brand.getName(), brand.getDiscount());
		}
	}

}
