package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void accSetup() {
		 accountsPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public  Object[] []  getProductSearchData() {
		return new Object[] [] {
			{"macbook","MacBook"},
			{"macbook","MacBook Pro"},
			{"imac","iMac"},
			{"samsung","Samsung SyncMaster 941BW"},
			{"samsung","Samsung Galaxy Tab 10.1"}
		};
	}
	
	@Test(priority = 1, dataProvider = "getProductSearchData")
	public void productHeaderTest(String searchKey, String productSelect) {
		searchPage=accountsPage.doSearch(searchKey);
		productinfo=searchPage.selectProduct(productSelect);
		Assert.assertEquals(productinfo.getproductHeader(), productSelect);
	}
	@DataProvider
	public  Object[] []  getProductInfoData() {
		return new Object[] [] {
			{"macbook","MacBook",5},
			{"macbook","MacBook Pro",4},
			{"imac","iMac",3},
			{"samsung","Samsung SyncMaster 941BW",1},
			{"samsung","Samsung Galaxy Tab 10.1",7}
		};
	}
	
	@Test(priority = 2, dataProvider = "getProductInfoData")
	public void productImageTest(String searchKey, String productSelect, int imagecount) {
		searchPage=accountsPage.doSearch(searchKey);
		productinfo=searchPage.selectProduct(productSelect);
		Assert.assertEquals(productinfo.getImageproductCount(), imagecount);
	}
	
	
	@Test(priority = 3)
	public void productInfoTest() {
		searchPage=accountsPage.doSearch("MacBook");
		productinfo=searchPage.selectProduct("MacBook Pro");
		Map<String, String> productDetailsMap = productinfo.getProductDetailsMap();
		softAssert.assertEquals(productDetailsMap.get("Brand"), "Apple");
		softAssert.assertEquals(productDetailsMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(productDetailsMap.get("Reward Points"), "800");
		softAssert.assertEquals(productDetailsMap.get("ExtraPrice"), "$2,000.00");
		softAssert.assertEquals(productDetailsMap.get("actualprice"), "$2,000.00");
		softAssert.assertAll();
	}
	
	
}
