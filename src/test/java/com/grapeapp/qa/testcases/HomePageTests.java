package com.grapeapp.qa.testcases;

import org.testng.Assert;

import com.grapeapp.qa.base.Testbase;

public class HomePageTests extends Testbase {
	
	public HomePageTests() {
		super();
	}
	
	public void testTitle() {
		String actual = home.getTitle();
		String expected = "CRMPRO";
		Assert.assertEquals(actual, expected);
	}

}
