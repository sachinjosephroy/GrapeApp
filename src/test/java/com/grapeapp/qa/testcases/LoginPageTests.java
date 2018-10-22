package com.grapeapp.qa.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.grapeapp.qa.base.Testbase;
import com.grapeapp.qa.logs.WebEventListener;

public class LoginPageTests extends Testbase {
	
	public LoginPageTests() {
		super();
	}
	
	@Test(priority=1)
	public void testTitle() {
		String actual = login.getTitle();
		String expected = "#1 Free CRM software in the cloud for sales and service";
		verifyTestResult(actual, expected);
		Assert.assertEquals(actual, expected);
	}
	
	@Test(priority=2)
	public void testLogo() {
		String actual = login.getLogo();
		String expected = "#1 Free CRM software in the cloud for sales and service";
		verifyTestResult(actual, expected);
		Assert.assertEquals(actual, expected);
	}

}
