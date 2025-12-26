package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.io.FileHandler;

import com.qa.opencart.expections.BrowserExpection;
import com.qa.opencart.expections.ElementExpection;
import com.qa.opencart.logger.Log;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tLDriver=new ThreadLocal<WebDriver>();

	public WebDriver initDriver(Properties prop) {

		
		optionsManager=new OptionsManager(prop);
		String browserName = prop.getProperty("browser");

		switch (browserName.trim().toLowerCase()) {
		case "chrome":
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tLDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
		case "edge":
			//driver = new EdgeDriver(optionsManager.getEdgeOptions());
			tLDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;

		default:
			//System.out.println("pls pass right browser");
			Log.error("pls pass right browser"+browserName);
			throw new BrowserExpection("NO BROWSER FOUND__" + browserName);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	
	public static WebDriver getDriver() {
		return tLDriver.get();
	}

	public Properties intiprop() {
		prop = new Properties();
		FileInputStream ip =null;
		
		//mvn clean install -Denv="qa"
		//cd eclipse-workspace\Dec2025POMSeries

		
		
		String envName = System.getProperty("env");
		//System.out.println("=======Running tests on Env======:  "+envName);
		Log.info("=======Running tests on Env======:  "+envName);
		
		try {
		
		if (envName==null) {
			//System.out.println("======NO ENV IS GIVEN,, HENCE RUNNING ON QA=====");
			Log.info("======NO ENV IS GIVEN,, HENCE RUNNING ON QA=====");
			 ip = new FileInputStream("./src/test/resourcess/config/config.qa.properties");
		}
		else {
		switch (envName.toLowerCase().trim()) {
		case "qa":
			 ip = new FileInputStream("./src/test/resourcess/config/config.qa.properties");
			break;
		case "stage":
			 ip = new FileInputStream("./src/test/resourcess/config/config.stage.properties");
			break;
		case "dev":
			 ip = new FileInputStream("./src/test/resourcess/config/config.stage.properties");
			break;
		case "uat":
			 ip = new FileInputStream("./src/test/resourcess/config/config.stage.properties");
			break;
		case "prod":
			 ip = new FileInputStream("./src/test/resourcess/config/config.properties");
			break;
		default:
			//System.out.println("======PLS RIGHT BROWSER======="+envName);
			Log.error("======PLS RIGHT BROWSER======="+envName);
			throw new ElementExpection("=====WRONG ENV======="+envName);
		
		}}}
		catch (FileNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
	
	/**
	 * take screenshot
	 */

	public static String getScreenshot(String methodName) {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp directory
		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()
				+ ".png";

		File destination = new File(path);

		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}
	

}
