package com.grapeapp.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.grapeapp.qa.base.Testbase;

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
