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
		String expected = login.getTitle();
		String actual = "CRMPRO";
		verifyTestResult(actual, expected);
		Assert.assertEquals(actual, expected);
	}

}
