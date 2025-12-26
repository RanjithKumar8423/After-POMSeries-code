package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class SearchResultPage {

	WebDriver driver;
	ElementUtil ele;

	private By productList=By.xpath("//div[@class='product-thumb']");

	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		ele = new ElementUtil(driver);
	}
	
	public int getsearchproductcount() {
		return ele.waitForElementsPresence(productList, TimeUtil.DEFAULT_SHORT_TIME).size();
	}
	
	public ProductInfoPage selectProduct(String productname) {
		System.out.println("searching product name: "+productname);
		ele.waitForElementPresence(By.linkText(productname), TimeUtil.DEFAULT_SHORT_TIME).click();
		return new ProductInfoPage(driver);
	}
}
