package com.grapeapp.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
	
	public String getCompanyPhoneFromGrid(String company, String phone, String email) {
		//util.hover(menuCompanies);
		//util.switchFrame();
		//JavascriptExecutor je = (JavascriptExecutor)driver;
	    //je.executeScript("arguments[0].scrollIntoView(true)", menuCompanies);
		/*Actions act = new Actions(driver);
		act.moveToElement(menuCompanies).build().perform();
		wait.untilElementIsVisible(menuItemNewCompany);*/
		//menuItemNewCompany.click();
		//driver.switchTo().defaultContent();
		util.switchFrame();
		menuCompanies.click();
		btnNewCompany.click();
		tbCompany.sendKeys(company);
		tbPhone.sendKeys(phone);
		tbEmail.sendKeys(email);
		saveBtn.click();
		menuCompanies.click();
		String text = txtPhone.getText();
		return text;
	}
	

}
