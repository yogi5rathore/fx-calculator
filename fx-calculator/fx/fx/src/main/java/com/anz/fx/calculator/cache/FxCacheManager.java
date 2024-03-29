package com.anz.fx.calculator.cache;

import static com.anz.fx.calculator.cache.FxCache.CURRENCY_MAP;
import static com.anz.fx.calculator.cache.FxCache.CURRENCY_MATRIX;
import static com.anz.fx.calculator.cache.FxCache.CURRENCY_PAIRS_RATE_MAP;
import static com.anz.fx.calculator.cache.FxCache.CURRENCY_PRECISION_MAP;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.anz.fx.calculator.FxApplication;

public class FxCacheManager {

	public static final String CURRENCY_MATRIX_FILE_PATH = "D:\\Personal\\ANZ\\config\\Currency_Matrix.xls";
	public static final String CURRENCY_PAIRS_RATE_FILE_PATH = "D:\\Personal\\ANZ\\config\\currency-pairs-rate.properties";
	public static final String CURRENCY_PRECISION_FILE_PATH = "D:\\Personal\\ANZ\\config\\currency-precision.properties";
	
	static Logger logger = LogManager.getLogger(FxApplication.class);

	public static void initCache() {
		FileReader reader = null;
		Workbook workbook = null;
		try {
			workbook = WorkbookFactory.create(new File(CURRENCY_MATRIX_FILE_PATH));
			Sheet sheet = workbook.getSheetAt(0);

			int rowNumber = sheet.getLastRowNum() + 1;
			int colNumber = sheet.getRow(0).getLastCellNum();

			FxCache.createCache(rowNumber, colNumber);

			/** Get first row, this will give all the available currency */
			Row firstRow = sheet.getRow(0);
			String currCellValue = null;
			for (int cellIndex = 0; cellIndex <= firstRow.getLastCellNum(); cellIndex++) {
				if (firstRow.getCell(cellIndex) != null) {
					currCellValue = firstRow.getCell(cellIndex).getStringCellValue();
					CURRENCY_MAP.put(currCellValue.trim(), cellIndex);
				}
			}

			/** Prepare Currency Matrix */
			logger.info("**************Initializing CURRENCY_MATRIX***********************");
			//logger.info("\n");
			for (int rowIndex = 0; rowIndex < rowNumber; rowIndex++) {
				for (int colIndex = 0; colIndex < colNumber; colIndex++) {
					if (sheet.getRow(rowIndex) != null && sheet.getRow(rowIndex).getCell(colIndex) != null) {
						currCellValue = sheet.getRow(rowIndex).getCell(colIndex).getStringCellValue();
						CURRENCY_MATRIX[rowIndex][colIndex] = currCellValue != null ? currCellValue.trim() : null;
						//logger.info(CURRENCY_MATRIX[rowIndex][colIndex] + "\t");
					}

				}
				//logger.info("\n");
			}

			/** Prepare Currency Pairs Rate */
			logger.info("**************Initializing CURRENCY_PAIRS_RATE_MAP***********************");
			reader = new FileReader(CURRENCY_PAIRS_RATE_FILE_PATH);
			Properties properties = new Properties();
			properties.load(reader);

			for (String currencyPair : properties.stringPropertyNames()) {
				Double conversionRate = Double.valueOf(properties.getProperty(currencyPair));
				CURRENCY_PAIRS_RATE_MAP.put(currencyPair, conversionRate);
			}	
		
			
			reader = new FileReader(CURRENCY_PRECISION_FILE_PATH);
			Properties precisionProperties = new Properties();
			precisionProperties.load(reader);

			for (String currencyPrecision : precisionProperties.stringPropertyNames()) {
				Integer precision = Integer.valueOf(precisionProperties.getProperty(currencyPrecision));
				CURRENCY_PRECISION_MAP.put(currencyPrecision, precision);
			}	
			
			
			reader.close();
			workbook.close();

		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			logger.error(e.getMessage(), e);

		} finally {
			try {
				reader.close();
				workbook.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
			
		}

	}

}
