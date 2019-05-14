package com.anz.fx.calculator.util;

import static com.anz.fx.calculator.cache.FxCache.CURRENCY_MAP;

import com.anz.fx.calculator.exception.FxException;

public class FxValidation {

	/**
	 * @param base
	 * @param term
	 * @param currencyValue
	 * @throws FxException
	 */
	public static void validate(String base, String term, String currencyValue) throws FxException {

		if (base == null) {
			throw new FxException("Base Currency cannot be null");
		} else if (term == null) {
			throw new FxException("Term Currency cannot be null");
		} else if (currencyValue == null) {
			throw new FxException("Currency value cannot be null");
		} else if (!CURRENCY_MAP.containsKey(base)) {
			throw new FxException("Unable to find rate for " + base + "/" + term);
		} else if (!CURRENCY_MAP.containsKey(term)) {
			throw new FxException("Unable to find rate for " + base + "/" + term);
		} else if (currencyValue != null && !currencyValue.toLowerCase().equals(currencyValue.toUpperCase())) {
			throw new FxException("Invalid Currency Value");
		}

	}

	/**
	 * @param base
	 * @param term
	 * @param crossCurrency
	 * @throws FxException
	 */
	public static void validateCrossCurrency(String base, String term, String crossCurrency) throws FxException {
		if (!CURRENCY_MAP.containsKey(crossCurrency)) {
			throw new FxException("Unable to find rate for " + base + "/" + term);
		}
	}

}
