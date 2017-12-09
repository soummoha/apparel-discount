package com.sg.shop.configuration;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sg.shop.model.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CategoryConfigurationTest {

	@Autowired
	private CategoryConfiguration categoryConfig;

	@Test
	public void testGetCategoryDiscountMap() {

		Map<String, Integer> categoryDiscountMap = categoryConfig.getCategoryDiscountMap();

		assertEquals(0, categoryDiscountMap.get("Shirts").intValue());
		assertEquals(50, categoryDiscountMap.get("Dresses").intValue());
		assertEquals(50, categoryDiscountMap.get("Footwear").intValue());
		assertEquals(20, categoryDiscountMap.get("Jeans").intValue());
		assertEquals(30, categoryDiscountMap.get("Casuals").intValue());
		assertEquals(0, categoryDiscountMap.get("Trousers").intValue());
	}

	@Test
	public void testGetCategories() {
		List<String> categories = categoryNames(categoryConfig.getCategories());

		assertTrue(categories.contains("Men's wear"));
		assertTrue(categories.contains("Women's wear"));

	}

	@Test
	public void testGetCategoriesMenWear() {
		List<String> mensCategories = categoryNames(mensCatagories());

		assertTrue(mensCategories.contains("Shirts"));
		assertTrue(mensCategories.contains("Jeans"));
		assertTrue(mensCategories.contains("Trousers"));

	}

	@Test
	public void testGetCategoriesWomenWear() {

		List<String> womensCategories = categoryNames(womensCategories());

		assertTrue(womensCategories.contains("Dresses"));
		assertTrue(womensCategories.contains("Footwear"));
	}
	

	private List<String> categoryNames(List<Category> categories) {
		List<String> categoryNames = categories.stream()
				.map(Category::getName)
				.collect(toList());
		
		return categoryNames;
	}

	private List<Category> womensCategories() {
		List<List<Category>> categoriesList = categoryConfig.getCategories().stream()
				.filter(c -> c.getName().equals("Women's wear"))
				.map(Category::getSubcategories)
				.collect(toList());
		
		return categoriesList.get(0);
	}

	private List<Category> mensCatagories() {
		List<List<Category>> categoriesList = categoryConfig.getCategories().stream()
				.filter(c -> c.getName().equals("Men's wear"))
				.map(Category::getSubcategories)
				.collect(toList());
		
		return categoriesList.get(0);
	}
}
