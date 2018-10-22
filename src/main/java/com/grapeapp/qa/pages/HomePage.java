package com.grapeapp.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.grapeapp.qa.base.Testbase;

public class HomePage extends Testbase {
	
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getTitle() {
		String title = driver.getTitle();
		return title;
	}
	
}
