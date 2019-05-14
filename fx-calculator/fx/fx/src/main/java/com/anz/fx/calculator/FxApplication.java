package com.anz.fx.calculator;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.anz.fx.calculator.cache.FxCacheManager;

@SpringBootApplication
public class FxApplication {

	static Logger logger = LogManager.getLogger(FxApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FxApplication.class, args);
		FxCacheManager.initCache();
		convertCurrency();

	}

	static void convertCurrency() {
		Scanner scanner = new Scanner(System.in);
		String inputString = scanner.nextLine();
		String result = FxCalculatorService.convertCurrency(inputString);
		System.out.println(result);
		scanner.close();
	}

}