package com.sg.shop.model;

public class Product {

	private String brand;

	private String category;

	private int price;

	private int productId;

	public Product(int productId, String brand, String category, int price) {
		this.productId = productId;
		this.brand = brand;
		this.category = category;
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public String getCategory() {
		return category;
	}

	public int getPrice() {
		return price;
	}

	public int getProductId() {
		return productId;
	}

}
