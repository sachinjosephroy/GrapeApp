package com.grapeapp.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.grapeapp.qa.base.Testbase;

public class LoginPage extends Testbase {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="username")
	WebElement tbUsername;
	
	@FindBy(name="password")
	WebElement tbPassword;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement btnLogin;
	
	@FindBy(xpath = "//div[@id='slide_1']//h1")
	WebElement txtLogo;
	
	@FindBy(xpath = "//td[@class='headertext'][1]")
	WebElement txtHeader;
	
	
	public void logMeIn() throws InterruptedException {
		tbUsername.sendKeys(prop.getProperty("username"));
		tbPassword.sendKeys(prop.getProperty("password"));
		util.jsClick(btnLogin);
		//Thread.sleep(1000);
		driver.switchTo().frame("mainpanel");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(txtHeader));
	}


	public String getTitle() {
		System.out.println("in Login with Chrome");
		return driver.getTitle();
	}


	public String getLogo() {
		return txtLogo.getText();
	}

}
