package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.AccountsPage;

public class AccountsPageTest extends BaseTest {

	
	@BeforeClass
	public void accSetup() {
		 accountsPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority = 1)
	public void acctitleTest() {
		String accPageTitle = accountsPage.getAccPageTitle();
		Assert.assertEquals(accPageTitle, "My Account");
	}
	@Test(priority = 2)
	public void accURLTest() {
		String accPageurl = accountsPage.getAccPageurl();
		Assert.assertTrue(accPageurl.contains("route=account/account"));
	}
	@Test(priority = 3)
	public void acclogoutLinksExist() {
		boolean logoutLinkExists = accountsPage.logoutLinkExists();
		Assert.assertTrue(logoutLinkExists);
	}
	@Test(priority = 4)
	public void accmyaccountExists() {
		boolean myaccExists = accountsPage.myaccountExists();
		Assert.assertTrue(myaccExists);
	}
	
	@Test(priority = 5)
	public void headersList() {
		List<String> allList = accountsPage.getAccountHeaderList();
		System.out.println(allList);
	}
	@Test(priority = 6)
	public void SearchTest() {
		accountsPage.doSearch("Macbook");
		
	}

}
