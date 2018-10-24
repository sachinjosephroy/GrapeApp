package com.grapeapp.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.grapeapp.qa.base.Testbase;

public class CompanyPage extends Testbase {
	
	WebDriver driver;
	
	public CompanyPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[@title='Companies']")
	WebElement menuCompanies;
	
	@FindBy(xpath = "//a[@title='New Company']")
	WebElement menuItemNewCompany;
	
	@FindBy(xpath = "//input[@id='company_name']")
	WebElement tbCompany;
	
	@FindBy(xpath = "//input[@id='phone']")
	WebElement tbPhone;
	
	@FindBy(xpath = "//input[@id='email']")
	WebElement tbEmail;
	
	@FindBy(xpath = "//form[@id='companyForm']//input[@type='submit']")
	WebElement saveBtn;
	
	@FindBy(xpath = "//input[@value='New Company']")
	WebElement btnNewCompany;
	
	@FindBy(xpath = "//span[@context='phone']")
	WebElement txtPhone;
	
	@FindBy(xpath = "//i[@title='Delete']")
	WebElement icnDelete;
	
	public String getCompanyPhoneFromGrid(String comp, String phone, String email) {
		util.hover(menuCompanies);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", menuItemNewCompany);
		tbCompany.sendKeys(comp);
		tbPhone.sendKeys(phone);
		tbEmail.sendKeys(email);
		saveBtn.click();
		menuCompanies.click();
		String text = txtPhone.getText();
		return text;
	}
	
	public void deleteCompany() {
		icnDelete.click();
		driver.switchTo().alert().accept();
	}

}
