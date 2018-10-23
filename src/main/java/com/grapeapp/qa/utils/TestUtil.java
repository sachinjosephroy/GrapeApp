package com.grapeapp.qa.utils;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.grapeapp.qa.base.Testbase;

public class TestUtil extends Testbase {
	
	static Workbook book;
	static Sheet sheet;
	static String excelDataFilePath = "C:\\ExtentReports\\ExcelData.xlsx";
	
	public void jsClick(WebElement elem) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", elem);
	}
	
	public void switchFrame() {
		driver.switchTo().frame("mainpanel");
	}
	
	public void hover(WebElement elem) {
		Actions act = new Actions(driver);
		act.moveToElement(elem).build().perform();
		System.out.println("I am here");
	}

	public static Object[][] getExcelData() {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(excelDataFilePath);
			book = WorkbookFactory.create(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		sheet = book.getSheet("company");
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for(int i=0; i<sheet.getLastRowNum(); i++) {
			for(int j=0; j<sheet.getRow(0).getLastCellNum(); j++) {
				data[i][j] = sheet.getRow(i+1).getCell(j).toString();
			}
		}
		return data;
	}

}
