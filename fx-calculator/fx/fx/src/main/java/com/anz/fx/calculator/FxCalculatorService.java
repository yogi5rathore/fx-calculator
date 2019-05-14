package com.anz.fx.calculator;

import static com.anz.fx.calculator.cache.FxCache.CURRENCY_MAP;
import static com.anz.fx.calculator.cache.FxCache.CURRENCY_MATRIX;
import static com.anz.fx.calculator.cache.FxCache.CURRENCY_PRECISION_MAP;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.anz.fx.calculator.constant.FeedEnum;
import com.anz.fx.calculator.exception.FxException;
import com.anz.fx.calculator.util.FxValidation;

@Service
public class FxCalculatorService {

	static Logger logger = LogManager.getLogger(FxCalculatorService.class);

	/**
	 * @param inputString
	 * @return
	 */
	public static String convertCurrency(String inputString) {

		// Replace empty string
		String currencyString = inputString.replace(" ", "");
		String base = currencyString.substring(0, 3);
		String term = currencyString.substring(currencyString.length() - 3);
		String currencyValueString = currencyString.substring(3, currencyString.indexOf('i'));

		logger.info("Base - " + base);
		logger.info("Term - " + term);
		logger.info("Base Currency - " + currencyValueString);

		String resultString = null;

		try {
			FxValidation.validate(base, term, currencyValueString);

			Double currency = Double.valueOf(currencyValueString);

			String selectedFeed = null;
			FeedEnum feed = null;
			String tmpBase = base;
			double result = currency;
			while (feed == null) {
				selectedFeed = CURRENCY_MATRIX[CURRENCY_MAP.get(tmpBase)][CURRENCY_MAP.get(term)];								
				feed = FeedEnum.findByAbbreviation(selectedFeed);
				if (feed != null) {
					selectedFeed = term;
				}
				FxValidation.validateCrossCurrency(base, term, selectedFeed);				
				result = FxCalculator.convertFromBaseToTerm(tmpBase, selectedFeed, result);
				tmpBase = selectedFeed;
			}

			int termPrecison = CURRENCY_PRECISION_MAP.get(term);
			String termResultString = String.format("%." + termPrecison + "f", result);

			int basePrecison = CURRENCY_PRECISION_MAP.get(base);
			String baseResultString = String.format("%." + basePrecison + "f", currency);

			resultString = base + " " + baseResultString + " = " + term + " " + termResultString;
		} catch (FxException e) {
			resultString = e.getMessage();
			logger.error(e.getMessage(), e);
		}

		return resultString;

	}
}
