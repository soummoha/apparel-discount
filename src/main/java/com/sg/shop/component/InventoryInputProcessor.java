package com.sg.shop.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sg.shop.model.Product;
import com.sg.shop.repository.InventoryRepository;

@Component ("inventoryInputProcessor")
public class InventoryInputProcessor implements InputProcessor {

	@Autowired
	@Qualifier("inventoryRepositoryImpl")
	private InventoryRepository inventoryRepository;

	@Override
	public void process(Scanner scanner) {
		System.out.println("\nPlease enter a valid inventory input or ctrl+z to exit");

		List<Product> productIds = new ArrayList<>();
		int selectedProducts = 0;

		selectedProducts = scanner.nextInt();

		if (selectedProducts <= 0) {
			return;
		}
		scanner.nextLine();

		while (scanner.hasNext()) {

			String userInput = scanner.nextLine();

			if (userInput.isEmpty() || !userInput.contains(",")) {
				return;
			}

			String[] items = userInput.split(",");

			if (items.length != 4) {
				break;
			}

			int productId = 0;
			String brand = null;
			String category = null;
			int price = 0;

			try {
				productId = Integer.valueOf(items[0].trim());
				price = Integer.valueOf(items[3].trim());

				if (productId <= 0 || price <= 0) {
					throw new RuntimeException();
				}
			} catch (RuntimeException e) {
				break;
			}

			brand = items[1].trim();
			category = items[2].trim();

			if (brand.isEmpty() || category.isEmpty()) {
				break;
			}

			productIds.add(new Product(productId, brand, category, price));

			if (productIds.size() == selectedProducts) {
				break;
			}

		}
		
		inventoryRepository.setAllProducts(productIds);

		System.out.println("Products added to inventory");

	}

}
