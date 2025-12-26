package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class ProductInfoPage {
	WebDriver driver;
	ElementUtil ele;

	private By productheader=By.tagName("h1");
	private By images=By.xpath("//ul[@class='thumbnails']/li");
	private By metaData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By priceDara=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	private Map<String, String> productMap= new HashMap<String, String>();

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		ele = new ElementUtil(driver);
	}
	public String getproductHeader() {
		String headername = ele.waitForElementPresence(productheader, TimeUtil.DEFAULT_SHORT_TIME).getText();
		System.out.println("Header Name is:  "+headername);
		return headername;
	}
	public int getImageproductCount() {
		int totalimg = ele.waitForElementsPresence(images, TimeUtil.DEFAULT_SHORT_TIME).size();
		System.out.println("Total image count is:  "+totalimg);
		return totalimg;
	}
	
//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: Out Of Stock
	private void getproductmetaData() {
		List<WebElement> metaList = ele.getElements(metaData);
		for(WebElement e:metaList) {
			String text = e.getText();
			String metakey = text.split(":")[0].trim();
			String metavalue = text.split(":")[1].trim();
			productMap.put(metakey, metavalue);
		}
		
	}
//	$2,000.00
//	Ex Tax: $2,000.00
		private void getpricemetaData() {
			List<WebElement> priceList = ele.getElements(priceDara);
			String priceproduct = priceList.get(0).getText();
			String accPriceValue = priceList.get(1).getText().split(":")[1].trim();
			productMap.put("actualprice", priceproduct);
			productMap.put("ExtraPrice", accPriceValue);
	}
		
		public Map<String, String> getProductDetailsMap() {
			productMap.put("Header", getproductHeader());
			productMap.put("ImagesCount", String.valueOf(getImageproductCount()));
			getproductmetaData();
			getpricemetaData();
			return productMap;
			
		}
		

	
}
