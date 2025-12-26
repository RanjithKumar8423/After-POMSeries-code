package com.qa.opencart.pages;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.logger.Log;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class AccountsPage {

	WebDriver driver;
	ElementUtil ele;
	
	
	private By logoutLink =By.linkText("Logout");
	private By myAccountLink = By.linkText("My Account");
	private By headers = By.xpath("//ul[@class='list-unstyled']/following-sibling::h2");
	private By search= By.name("search");
	private By searchIcon= By.xpath("//button[@class='btn btn-default btn-lg']");
	

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		ele = new ElementUtil(driver);
	}
	
	public String getAccPageTitle() {
		String title = ele.waitForTitleContains(TimeUtil.DEFAULT_SHORT_TIME, "My Account");
		//System.out.println("Page title is  " + title);
		Log.info("Page title is  " + title);
		return title;
	}

	public String getAccPageurl() {
		String url = ele.waitForURLIs(TimeUtil.DEFAULT_SHORT_TIME, "route=account/account");
		//System.out.println("Page title is  " + url);
		Log.info("Page title is  " + url);
		return url;
	}
	public boolean logoutLinkExists() {
		return ele.isElementDisplayed(logoutLink);
	}
	public boolean myaccountExists() {
		return ele.isElementDisplayed(myAccountLink);
	}
	public List<String> getAccountHeaderList() {
		List<WebElement> eleCount = ele.getElements(headers);
		List<String> allHeaders=new ArrayList<String>(); 
		for(WebElement e:eleCount) {
			String text = e.getText();
			allHeaders.add(text);
		}
		return allHeaders;
	}
	
	public SearchResultPage doSearch(String searchkey) {
		//System.out.println("THese your are Searching:  "+searchkey);
		Log.info("THese your are Searching:  "+searchkey);
		ele.doSendKey(search, searchkey, TimeUtil.DEFAULT_SHORT_TIME);
		ele.doclick(searchIcon, TimeUtil.DEFAULT_SHORT_TIME);
		return new SearchResultPage(driver);
	}
	
}
