package com.grapeapp.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.grapeapp.qa.base.Testbase;
import com.grapeapp.qa.utils.TestUtil;

public class CompanyPageTests extends Testbase {
	
	public CompanyPageTests() {
		super();
	}
	
	@DataProvider
	public Object[][] getTestData() {
		Object data[][] = TestUtil.getExcelData();
		return data;
	}
	
	@Test(priority=1, dataProvider="getTestData")
	public void testCompanyPhoneFromGrid(String comp, String phone, String email) throws InterruptedException {
		login.logMeIn();
		String actual = company.getCompanyPhoneFromGrid(comp, phone, email);
		String expected = "(212) 985-9999";
		verifyTestResult(actual, expected);
		Assert.assertEquals(actual, expected);
	}

}
