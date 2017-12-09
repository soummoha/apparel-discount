package com.sg.shop.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sg.shop.model.Product;
import com.sg.shop.repository.InventoryRepository;
import com.sg.shop.service.BillingService;

@Component("productInputProcessor")
public class ProductInputProcessor implements InputProcessor {
	
	@Autowired
	@Qualifier("inventoryRepositoryImpl")
	private InventoryRepository inventoryRepository;
	
	@Autowired
	@Qualifier("billingServiceImpl")
	private BillingService billingService;

	@Override
	public void process(Scanner scanner) {
		System.out.println("\nPlease enter a valid input to generate bills of products or ctrl+z to exit");

		int selectedProducts = 0;
		
		selectedProducts = scanner.nextInt();

		if (selectedProducts <= 0) {
			return;
		}
		
		scanner.nextLine();
		
		List<Integer> bills = new ArrayList<>();
		
		int count = 0;
		
		while (scanner.hasNext()) {
			
			List<Integer> productIds = new ArrayList<>();
			
			String userInput = scanner.nextLine();
			
			if (userInput.isEmpty() || !userInput.contains(",")) {
				return;
			}
			
			String[] items = userInput.split(",");			

			for (int index = 0; index < items.length; index++) {
				try {
					Integer id = Integer.valueOf(items[index].trim());
					if (id <= 0) {
						throw new RuntimeException();
					}
					productIds.add(id);
				} catch (RuntimeException e) {
					return;
				}
			}
			
			if (!inventoryRepository.containsAll(productIds)) {
				return;
			}
			
			List<Product> products = inventoryRepository.getProducts(productIds);
			
			bills.add(billingService.makeBill(products));
			
			count++;
			
			if (count == selectedProducts) {
				break;
			}
			
		}
		
		bills.forEach(System.out::println);

	}

}
