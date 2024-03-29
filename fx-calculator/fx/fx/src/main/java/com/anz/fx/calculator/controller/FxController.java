package com.anz.fx.calculator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anz.fx.calculator.FxCalculatorService;

@RestController
@RequestMapping(path = "/currency")
public class FxController {
	
	@GetMapping(path = "/convert/{input}", produces = "application/json")
	public String getEmployees(@PathVariable("input") String inputString) {
		return FxCalculatorService.convertCurrency(inputString);
	}
}
