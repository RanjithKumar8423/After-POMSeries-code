package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchResultPage;

public class BaseTest {
	WebDriver driver;
	DriverFactory df;
	protected LoginPage loginpage;
	protected Properties prop;
	protected AccountsPage accountsPage;
	protected SearchResultPage searchPage;
	protected ProductInfoPage productinfo;
	protected SoftAssert softAssert;
	protected RegistrationPage registrationPage;
	
	@Parameters({"browser"})
	@BeforeTest
	public void setup(String browserName) {
		df=new DriverFactory();
		prop=df.intiprop();
		if (browserName !=null) {
			prop.setProperty("browser", browserName);
		}
		driver=df.initDriver(prop);
		loginpage=new LoginPage(driver);
		softAssert=new SoftAssert();
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
