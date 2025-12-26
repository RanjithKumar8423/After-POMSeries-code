package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class SearchResultPageTest extends BaseTest {

	
	@BeforeClass
	public void searchSetup() {
		accountsPage= loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public  Object[] []  getProductCountData() {
		return new Object[] [] {
			{"macbook",3},
			{"imac",1},
			{"samsung",2}
		};
	}
	
	@Test(dataProvider="getProductCountData")
	public void searchresultTest(String productName, int imagecount) {
		searchPage=accountsPage.doSearch(productName);
		Assert.assertEquals(searchPage.getsearchproductcount(), imagecount);
	}
}
