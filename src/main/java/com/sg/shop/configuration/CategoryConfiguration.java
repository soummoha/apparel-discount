package com.sg.shop.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.sg.shop.model.Category;

/**
 * The configuration class reads all the product categories configurations from
 * <file>application.yml<file>
 *
 */
@ConfigurationProperties(prefix = "product")
@Service
public class CategoryConfiguration {

	private List<Category> categories = new ArrayList<>();

	private Map<String, Integer> categoryDiscountMap = new HashMap<>();

	public List<Category> getCategories() {
		return categories;
	}

	public Map<String, Integer> getCategoryDiscountMap() {
		return categoryDiscountMap;
	}

	@PostConstruct
	public void postConstruct() {
		for (Category category : categories) {
			updateAncestorDiscount(category.getDiscount(), category.getSubcategories());
		}
	}

	private void updateAncestorDiscount(int ancestorDiscount, List<Category> categories) {
		if (categories == null || categories.isEmpty()) {
			return;
		}
		categories.forEach(category -> {
			int categoryDiscount = category.getDiscount();
			category.setDiscount(ancestorDiscount + categoryDiscount);
			categoryDiscountMap.put(category.getName(), category.getDiscount());
			updateAncestorDiscount(category.getDiscount(), category.getSubcategories());
		});
	}

}
