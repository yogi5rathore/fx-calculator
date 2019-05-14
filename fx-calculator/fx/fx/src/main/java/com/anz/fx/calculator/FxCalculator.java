package com.anz.fx.calculator;

import static com.anz.fx.calculator.cache.FxCache.CURRENCY_MAP;
import static com.anz.fx.calculator.cache.FxCache.CURRENCY_MATRIX;
import static com.anz.fx.calculator.cache.FxCache.CURRENCY_PAIRS_RATE_MAP;

import com.anz.fx.calculator.constant.FeedEnum;

public class FxCalculator {

	/**
	 * @param base
	 * @param term
	 * @param value
	 * @return
	 */
	public static double convertFromBaseToTerm(String base, String term, Double value) {
		String selectedFeed = CURRENCY_MATRIX[CURRENCY_MAP.get(base)][CURRENCY_MAP.get(term)];
		FeedEnum feed = FeedEnum.findByAbbreviation(selectedFeed);		
		Double rate = CURRENCY_PAIRS_RATE_MAP.get(base + term);
		if (rate == null) {
			rate = CURRENCY_PAIRS_RATE_MAP.get(term + base);
		}
		double result = 0;
		switch (feed) {
		case DIRECT:
			result = value * rate;
			break;

		case UNITY:
			result = value;
			break;

		case INVERSION:
			result = value / rate;
			break;

		}
		return result;
	}

}
