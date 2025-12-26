package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

import io.qameta.allure.Step;

public class LoginPage {

	WebDriver driver;
	ElementUtil ele;

	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//input[@value='Login']");
	private By forgotPWdLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		ele = new ElementUtil(driver);
	}

	@Step("These is my Login Page Title")
	public String getLoginPageTitle() {
		String title = ele.waitForTitleContains(TimeUtil.DEFAULT_SHORT_TIME, "Account Login");
		System.out.println("Page title is  " + title);
		return title;
	}
	@Step("These is my Login Page URL")
	public String getLoginPageurl() {
		String url = ele.waitForURLIs(TimeUtil.DEFAULT_SHORT_TIME, "route=account");
		System.out.println("Page title is  " + url);
		return url;
	}
	@Step("These is my Login Page Forgot Link")
	public boolean isForgotPwdLinkExist() {
		return ele.isElementDisplayed(emailId);

	}
	@Step("These is my Login Page Do Login Operation{0},{1}")
	public AccountsPage doLogin(String username, String pwd) {
		System.out.println("user creds: " + username + " : " + pwd);
		ele.doSendKey(emailId, username, TimeUtil.DEFAULT_SHORT_TIME);
		ele.doSendKey(password, pwd);
		ele.doclick(loginButton);
		return new AccountsPage(driver);
	}
	public RegistrationPage navigateToRegister() {
		ele.waitForElementPresence(registerLink, 5).click();
		return new RegistrationPage(driver);
	}

}
