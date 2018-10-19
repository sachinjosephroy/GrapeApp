package com.grapeapp.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.grapeapp.qa.logs.WebEventListener;
import com.grapeapp.qa.pages.LoginPage;
import com.grapeapp.qa.utils.TestUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Testbase {
	
	String configFilePath = "C:\\Users\\simir\\eclipse-workspace\\GrapeApp\\src\\main\\java\\com\\grapeapp\\qa\\configuration\\config.properties";
	String extentReportPath = "C:\\Users\\simir\\eclipse-workspace\\GrapeApp\\ExtentReports\\GrapeAppReport.html";
	String extentConfigPath = "C:\\Users\\simir\\eclipse-workspace\\GrapeApp\\ExtentReports\\extent-config.xml";
	public static WebDriver driver;
	public static Properties prop;
	public static ExtentReports extent;
	public static ExtentTest test;
	EventFiringWebDriver e_driver;
	WebEventListener eListener;
	
	public LoginPage login;
	public static TestUtil util;
	
	public Testbase() {
		try {
			FileInputStream fis = new FileInputStream(configFilePath);
			prop = new Properties();
			prop.load(fis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void initializeBrowser() {
		//String browserName = prop.getProperty("browser");
		String osName = System.getProperty("os.name");
			if (osName.equalsIgnoreCase("windows 7")) {
				System.setProperty("webdriver.chrome.driver", prop.getProperty("chromedriverpath"));
				driver = new ChromeDriver();
			}
			else if (osName.equalsIgnoreCase("windows 10")) {
				System.setProperty("webdriver.gecko.driver", prop.getProperty("firefoxdriverpath"));
				driver = new FirefoxDriver();
			}
		
		e_driver = new EventFiringWebDriver(driver);
		eListener = new WebEventListener();
		e_driver.register(eListener);
		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		
		initializePages();
	}
	
	public void initializePages() {
		login = new LoginPage(driver);
		util = new TestUtil();
	}
	
	@BeforeSuite
	public void setUpSuite() {
		extent = new ExtentReports(extentReportPath);
		extent.loadConfig(new File(extentConfigPath));
	}
	
	@BeforeMethod
	public void setUpMethod(Method method) {
		String osName = System.getProperty("os.name");
		test = extent.startTest(osName + " :: " + this.getClass().getSimpleName() + " :: " + method.getName(), method.getName());
		test.assignAuthor("Sachin Roy");
		test.assignCategory("Functional Tests");
		initializeBrowser();
		initializePages();
	}
	
	@AfterMethod
	public void tearDownMethod(ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, "Testing failed");
			extent.endTest(test);
		}
		else if(result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, "Testing skipped");
			extent.endTest(test);
		}
		else if(result.getStatus() == ITestResult.SUCCESS) {
			test.log(LogStatus.PASS, "Testing passed");
			extent.endTest(test);
		}
		driver.quit();
		
	}
	
	@AfterSuite
	public void tearDownSuite() {
		extent.flush();
		extent.close();
	}
	
	public void verifyTestResult(String actual, String expected) {
		if(!actual.equals(expected)) {
			test.log(LogStatus.FAIL, "EXP RESULT: " + expected + "<br/>" + "ACT RESULT: " + actual);
		}
	}
	
}
