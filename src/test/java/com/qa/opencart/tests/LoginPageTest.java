package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.AccountsPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("Epic 100: Design Open Cart Login Test")
@Story("story 20:  Design Open cart Login Application for Stroy")
@Feature("Feature 394: Adding Login Feature")
public class LoginPageTest extends BaseTest {
	
	@Description("Checking Login Page Title")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void titleTest() {
		String acttile = loginpage.getLoginPageTitle();
		Assert.assertEquals(acttile, "Account Login");
	}
	
	@Description("Checking Login Page URL")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 2)
	public void urlTest() {
		String acturl = loginpage.getLoginPageurl();
		Assert.assertTrue(acturl.contains("route=account"));
	}
	
	@Description("Checking Login Page in forgetLinkExist")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void forgetLinkExistTest() {
		boolean forgetlink = loginpage.isForgotPwdLinkExist();
		Assert.assertTrue(forgetlink);
	}
	
	@Description("Checking DoLogin")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 4)
	public void doLogin() {
		accountsPage= loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accountsPage.getAccPageTitle(), "My Account");
	}
	
	
}
