package com.sg.shop.component;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * ConsoleInputRunner class implements <code>CommandLineRunner<code> which
 * accepts and processes the user input from command line. It will only invoke
 * if the profile is set to "prod".
 * 
 * @see CommandLineRunner
 * 
 */

@Component
@Profile("prod")
public class ConsoleInputRunner implements CommandLineRunner {

	@Autowired
	@Qualifier("inventoryInputProcessor")
	private InputProcessor inventoryInputProcessor;

	@Autowired
	@Qualifier("productInputProcessor")
	private InputProcessor productInputProcessor;

	@Override
	public void run(String... args) throws Exception {
		try (Scanner scanner = new Scanner(System.in)) {
			inventoryInputProcessor.process(scanner);
			productInputProcessor.process(scanner);
		}catch(Exception e){
			System.out.println("Invalid user input");
		} finally {
			System.exit(0);			
		}
	}
}
