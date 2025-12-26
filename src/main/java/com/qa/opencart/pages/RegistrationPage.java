package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class RegistrationPage {

	WebDriver driver;
	ElementUtil ele;
	
	private By fn = By.id("input-firstname");
	private By ln=By.id("input-lastname");
	private By emailID=By.name("email");
	private By mobile=By.name("telephone");
	private By pwd=By.id("input-password");
	private By confrpwd=By.id("input-confirm");
	private By subscribeyes=By.xpath("(//input[@type='radio' and @value='1'])[2]");
	private By subcribeNo=By.xpath("(//input[@type='radio' and @value='0'])[1]");
	private By checkbox=By.xpath("(//input[@type='checkbox' and @value='1'])[1]");
	private By continuebtn=By.xpath("(//input[@type='submit' and @value='Continue'])[1]");
	private By successMessg = By.tagName("h1");
	private By registerLink = By.linkText("Register");
	private By logoutLink=By.linkText("Logout");
	
	
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		ele = new ElementUtil(driver);
	}
	public boolean userRegister(String fn, String ln, String mobile, String pwd,String subscribe,String emailID ) {
		ele.waitForElementPresence(this.fn, 7).sendKeys(fn);
		ele.doSendKey(this.ln, ln);
		ele.doSendKey(this.mobile, mobile);
		ele.doSendKey(this.pwd, pwd);
		ele.doSendKey(this.emailID, emailID);
		ele.doSendKey(this.confrpwd, pwd);
		if(subscribe.equalsIgnoreCase("yes")) {
			ele.doclick(subscribeyes);
		}
		else {
			ele.doclick(subcribeNo);
		}
		ele.doclick(checkbox);
		ele.doclick(continuebtn);
		String regcardSuccesmes = ele.waitForElementPresence(successMessg, TimeUtil.DEFAULT_SHORT_TIME).getText();
		System.out.println(regcardSuccesmes);
		if (regcardSuccesmes.contains("Your Account Has Been")) {
			ele.doclick(logoutLink);
			ele.doclick(registerLink);
			return true;
		}
		return false;
	}
}
