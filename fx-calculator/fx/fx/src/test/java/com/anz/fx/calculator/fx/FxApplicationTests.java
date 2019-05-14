package com.anz.fx.calculator.fx;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.anz.fx.calculator.FxCalculatorService;
import com.anz.fx.calculator.cache.FxCacheManager;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FxApplicationTests {

	@Before
	public void initCache() {
		FxCacheManager.initCache();
	}

	@Test
	public void convertCurrency_success_AUD_USD() {
		String inputString = "AUD 100.00 in USD";
		String result = FxCalculatorService.convertCurrency(inputString);
		System.out.println(result);

		Assert.assertTrue("AUD 100.00 = USD 83.71".equals(result));

	}
	
	@Test
	public void convertCurrency_success_AUD_AUD() {
		String inputString = "AUD 100.00 in AUD";
		String result = FxCalculatorService.convertCurrency(inputString);
		System.out.println(result);

		Assert.assertTrue("AUD 100.00 = AUD 100.00".equals(result));

	}
	
	@Test
	public void convertCurrency_success_AUD_DKK() {
		String inputString = "AUD 100.00 in DKK";
		String result = FxCalculatorService.convertCurrency(inputString);
		System.out.println(result);

		Assert.assertTrue("AUD 100.00 = DKK 505.76".equals(result));

	}
	
	@Test
	public void convertCurrency_success_JPY_USD() {
		String inputString = "JPY 100 in USD";
		String result = FxCalculatorService.convertCurrency(inputString);
		System.out.println(result);

		Assert.assertTrue("JPY 100 = USD 0.83".equals(result));

	}

	@Test
	public void convertCurrency_invalid_KRW_FJD() {
		String inputString = "KRW 1000.00 in FJD";
		String result = FxCalculatorService.convertCurrency(inputString);
		System.out.println(result);

		Assert.assertTrue("Unable to find rate for KRW/FJD".equals(result));

	}

}
