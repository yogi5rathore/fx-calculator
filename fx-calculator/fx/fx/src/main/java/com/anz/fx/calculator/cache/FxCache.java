package com.anz.fx.calculator.cache;

import java.util.HashMap;
import java.util.Map;

public class FxCache {
	
	public static String [][]CURRENCY_MATRIX;
	public static Map<String,Double>CURRENCY_PAIRS_RATE_MAP;
	public static Map<String,Integer>CURRENCY_MAP;
	public static Map<String,Integer>CURRENCY_PRECISION_MAP;
	
	public static void createCache(int row, int col) {
		CURRENCY_MATRIX = new String [row][col];
		CURRENCY_PAIRS_RATE_MAP = new HashMap<>();
		CURRENCY_MAP = new HashMap<>();
		CURRENCY_PRECISION_MAP = new HashMap<>();
	}

}
