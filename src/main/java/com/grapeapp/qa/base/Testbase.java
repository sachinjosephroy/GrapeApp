package com.grapeapp.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
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
import com.grapeapp.qa.pages.CompanyPage;
import com.grapeapp.qa.pages.HomePage;
import com.grapeapp.qa.pages.LoginPage;
import com.grapeapp.qa.utils.TestUtil;
import com.grapeapp.qa.wait.Wait;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Testbase {
	
	String configFilePathWin10 = "C:\\Users\\simir\\eclipse-workspace\\GrapeApp\\src\\main\\java\\com\\grapeapp\\qa\\configuration\\config.properties";
	String configFilePathWin7 = "C:\\Users\\Sachin Roy\\eclipse-oxygen-workspace\\GrapeApp\\src\\main\\java\\com\\grapeapp\\qa\\configuration\\config.properties"; 
	String extentReportPath = "C:\\ExtentReports\\GrapeAppReport.html";
	String extentConfigPath = "C:\\ExtentReports\\extent-config.xml";
	public static WebDriver driver;
	public static Properties prop;
	public static ExtentReports extent;
	public static ExtentTest test;
	String browsername;
	String browserversion;
	EventFiringWebDriver e_driver;
	WebEventListener eListener;
	
	public LoginPage login;
	public HomePage home;
	public CompanyPage company;
	public static TestUtil util;
	public static Wait wait;
	
	public String getConfigFilePath() {
		String configFilePath = null;
		if(System.getProperty("os.name").equalsIgnoreCase("Windows 10")) {
			configFilePath = configFilePathWin10;
		}
		else if(System.getProperty("os.name").equalsIgnoreCase("Windows 7")) {
			configFilePath = configFilePathWin7;
		}
		
		return configFilePath;
	}
	
	public Testbase() {
		try {
			FileInputStream fis = new FileInputStream(getConfigFilePath());
			prop = new Properties();
			prop.load(fis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void initializeBrowser() {
		String osName = System.getProperty("os.name");
			if (osName.equalsIgnoreCase("windows 10")) {
				System.setProperty("webdriver.chrome.driver", prop.getProperty("chromedriverpath"));
				driver = new ChromeDriver();
				Capabilities cap = ((ChromeDriver) driver).getCapabilities();
				browsername = cap.getBrowserName().toUpperCase();
				browserversion = cap.getVersion();
				test.setDescription("Operating System: " + osName.toUpperCase() + ", " + "<br />" + "Browser: " + browsername + " " + browserversion);
			}
			else if (osName.equalsIgnoreCase("windows 10")) {
				System.setProperty("webdriver.gecko.driver", prop.getProperty("firefoxdriverpath"));
				driver = new FirefoxDriver();
				Capabilities cap = ((FirefoxDriver) driver).getCapabilities();
				browsername = cap.getBrowserName().toUpperCase();
				
				/*System.setProperty("webdriver.edge.driver", prop.getProperty("edgedriverpath"));
				driver = new EdgeDriver();
				Capabilities cap = ((EdgeDriver) driver).getCapabilities();
				browsername = cap.getBrowserName().toUpperCase();*/
				
				test.setDescription("Operating System: " + osName.toUpperCase() + ", " + "<br />" + "Browser: " + browsername);
			}
		
		e_driver = new EventFiringWebDriver(driver);
		eListener = new WebEventListener();
		e_driver.register(eListener);
		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		
		//initializePages();
	}
	
	public void initializePages() {
		login = new LoginPage(driver);
		home = new HomePage(driver);
		company = new CompanyPage(driver);
		util = new TestUtil();
		wait = new Wait();
	}
	
	@BeforeSuite
	public void setUpSuite() {
		extent = new ExtentReports(extentReportPath);
		extent.loadConfig(new File(extentConfigPath));
	}
	
	@BeforeMethod
	public void setUpMethod(Method method) {
		test = extent.startTest(this.getClass().getSimpleName() + " :: " + method.getName());
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
		//driver.quit();
		
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
		else {
			test.log(LogStatus.PASS, "EXP RESULT: " + expected + "<br/>" + "ACT RESULT: " + actual);
		}
	}
	
}
