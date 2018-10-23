package com.grapeapp.qa.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.grapeapp.qa.base.Testbase;

public class TestUtil extends Testbase {
	
	public void jsClick(WebElement elem) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", elem);
	}
	
	public void switchFrame() {
		driver.switchTo().frame("mainpanel");
	}

}
