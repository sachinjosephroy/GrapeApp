package com.grapeapp.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.grapeapp.qa.base.Testbase;

public class LoginPage extends Testbase {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "")
	WebElement tbUsername;
	
	@FindBy(xpath = "")
	WebElement tbPassword;
	
	@FindBy(xpath = "")
	WebElement btnLogin;
	
	
	public void logMeIn() {
		tbUsername.sendKeys(prop.getProperty("username"));
		tbPassword.sendKeys(prop.getProperty("password"));
		util.jsClick(btnLogin);
	}


	public String getTitle() {
		System.out.println("in Login with Chrome");
		return driver.getTitle();
	}

}
