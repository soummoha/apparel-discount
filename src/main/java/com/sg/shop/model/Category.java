package com.sg.shop.model;

import java.util.List;

public class Category {

	private int discount;

	private String name;

	private List<Category> subcategories;

	public int getDiscount() {
		return discount;
	}

	public String getName() {
		return name;
	}

	public List<Category> getSubcategories() {
		return subcategories;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSubcategories(List<Category> subcategory) {
		this.subcategories = subcategory;
	}

	@Override
	public String toString() {
		return "Category [name=" + name + ", discount=" + discount + ", subcategories=" + subcategories + "]";
	}

}
