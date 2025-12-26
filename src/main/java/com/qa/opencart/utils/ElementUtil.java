package com.qa.opencart.utils;


import static org.openqa.selenium.support.locators.RelativeLocator.with;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.expections.ElementExpection;

import io.qameta.allure.Step;

public class ElementUtil {

	private WebDriver driver;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}
	@Step("These is isElementDisplayed {0}")
	public boolean isElementDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}
	@Step("These is Do SendsKey Operation without Time, These locator {0}, these passing mes {1")
	public void doSendKey(By locator, String msg) {
		getElement(locator).clear();
		getElement(locator).sendKeys(msg);
	}

	@Step("These is Do SendsKey Operation with Time{2}, These locator {0}, these passing mes {1}")
	public void doSendKey(By locator, String msg, int time) {
		waitForElementVisible(locator, time).clear();
		waitForElementVisible(locator, time).sendKeys(msg);
	}
	@Step("These is doclick , these locator {0}")
	public void doclick(By locator) {
		getElement(locator).click();
	}

	public void doclick(By locator, int time) {
		waitForElementVisible(locator, time).click();
	}

	public String headerText(By locator) {
		return getElement(locator).getText();
	}

	public String emailgenrate() {
		String qunineemail = "Automation" + System.currentTimeMillis() + "@gmail.com";
		return qunineemail;
	}

	// -------------------------------
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public String doElementGetAttribute(By locator, String altrname) {
		return getElement(locator).getAttribute(altrname);
	}

	public boolean isElementExist(By locator) {
		if (getElements(locator).size() == 1) {
			return true;
		}
		return false;
	}

	public int getElementsCount(By locator) {
		return driver.findElements(locator).size();

	}

	public ArrayList<String> getElementAttributeList(By locator, String altrname) {
		List<WebElement> altributname = driver.findElements(locator);
		ArrayList<String> altercount = new ArrayList<String>();
		for (WebElement e : altributname) {
			String actaltercount = e.getAttribute(altrname);
			if (actaltercount.length() != 0) {
				altercount.add(actaltercount);
			}

		}
		return altercount;
	}

	public ArrayList<String> getElementsTextList(By locator) {
		List<WebElement> actText = driver.findElements(locator);
		ArrayList<String> textcount = new ArrayList<String>();
		for (WebElement e : actText) {
			String text = e.getText();
			if (text.length() != 0) {
				textcount.add(text);
			}

		}
		return textcount;
	}
	// ---------Select Class Util----------------------

	public void doselectByVisibleText(By locator, String textvisible) {
		Select select = new Select(getElement(locator));
		myexpectionEle(textvisible);
		select.selectByVisibleText(textvisible);

	}

	public void doselectByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public void doSelectByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		myexpectionEle(value);
		select.selectByValue(value);
	}

	public void myexpectionEle(String visibleText) {
		if (visibleText == null || visibleText.length() == 0) {
			throw new ElementExpection(visibleText + "  visible text can not be null");
		}

	}

	// --------GetOptions Method in Select class------------

	public List<WebElement> getDropDownOptionsList(By locator) {
		WebElement element = driver.findElement(locator);
		Select select = new Select(element);
		return select.getOptions();
	}

	public int countcountryselect(By locator) {
		return getDropDownOptionsList(locator).size();
	}

	public void doSelectDropDownValue(By locator, String value) {
		List<WebElement> optionList = getDropDownOptionsList(locator);
		for (WebElement e : optionList) {
			String text = e.getText();
			if (text.equals(value)) {
				e.click();
			}
		}

	}

	public List<String> getDropDownOptionsTextList(By locator) {

		List<WebElement> optionList = getDropDownOptionsList(locator);
		List<String> optionsTextList = new ArrayList<String>();
		for (WebElement e : optionList) {
			String text = e.getText();
			optionsTextList.add(text);
		}
		return optionsTextList;

	}

	public void DoSelectValueFromDropDown(By locator, String value) {
		List<WebElement> optionList = driver.findElements(locator);
		for (WebElement e : optionList) {
			String text = e.getText();
			if (text.equals(value)) {
				e.click();
			}
		}

	}

	// --------Google Search -----------
	public void doSearch(By searchlocator, String searchtext, By locateElement, String selectvalue)
			throws InterruptedException {
		doSendKey(searchlocator, searchtext);
		Thread.sleep(3000);
		List<WebElement> allElement = getElements(locateElement);
		for (WebElement e : allElement) {
			String text = e.getText();
			System.out.println(text);
			if (text.contains(selectvalue)) {
				e.click();
				break;
			}
		}
	}

	public void selectChoice(By clickelement, By choice, String... value) throws InterruptedException {
		doclick(clickelement);
		Thread.sleep(2000);
		List<WebElement> choiceList = getElements(choice);
		if (!value[0].equals("all")) {
			for (WebElement e : choiceList) {
				String text = e.getText();
				System.out.println(text);
				for (String val : value) {
					if (text.equals(val)) {
						e.click();

					}
				}
			}
		} else {
			for (WebElement e : choiceList) {
				try {
					e.click();
				} catch (Exception e2) {
					break;
				}

			}
		}

	}

	// ---------------Actions Class Element Utils--------------
	public void handleMenuSubMenuLevel2(By submenu1, By submenu2, By submenu3) throws InterruptedException {
		Actions act = new Actions(driver);
		act.click(getElement(submenu1)).perform();
		act.moveToElement(getElement(submenu2)).build().perform();
		act.click(getElement(submenu3)).perform();
	}

	public void handleMenuSubMenuLevel4(By level1, By level2, By level3, By level4) throws InterruptedException {

		Actions act = new Actions(driver);
		act.click(getElement(level1)).perform();
		Thread.sleep(3000);
		act.moveToElement(getElement(level2)).perform();
		Thread.sleep(3000);
		act.moveToElement(getElement(level3)).perform();
		Thread.sleep(3000);
		act.click(getElement(level4)).perform();

	}

	public Actions dragElementDrop(By locator1, By locator2) {
		Actions act = new Actions(driver);
//		act.clickAndHold(getElement(locator1)).moveToElement(getElement(locator2)).release().perform();
		return act.dragAndDrop(getElement(locator1), getElement(locator2));
	}

	public void acceptpopup() {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	public void doRightClick(By locator1, By locator2) {
		Actions act = new Actions(driver);
		act.contextClick(getElement(locator1)).perform();
		List<WebElement> allEle = getElements(locator2);
		for (WebElement e : allEle) {
			String text = e.getText();
			if (text.equals("Edit")) {
				e.click();
				break;

			}
		}

	}

	public void actionSendleys(By locator1, By locator2, By locator3, String value1, String value2) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator1), value1).perform();
		act.sendKeys(getElement(locator2), value2).perform();
		act.click(getElement(locator3)).perform();
	}

	// -------Actions END---------------

	// ---------Actions Scrolling-----------------\\
	public void click_pagescrolling(By locator1) {
		Actions act = new Actions(driver);
//		act.sendKeys(Keys.PAGE_DOWN).perform();
//		Thread.sleep(3000);
//		act.sendKeys(Keys.PAGE_UP).perform();
		act.moveToElement(getElement(locator1)).click(getElement(locator1)).perform();
	}

	public void sendkeypause(By locator1, String value) {
		Actions act = new Actions(driver);
		char[] charvalue = value.toCharArray();
		for (char e : charvalue) {
			act.sendKeys(getElement(locator1), value.valueOf(e)).pause(500).build().perform();
		}

	}

	public void tab_Sequence(By locator1, String value1) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator1), value1).perform();
		act.sendKeys(Keys.TAB).sendKeys(Keys.ENTER).perform();
		act.sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).perform();
	}

	// ---------AlertJsPopupHandling -----------------\\
	public void alert_ConfirmPopupJs(By locator1) throws InterruptedException {
		getElement(locator1).click();
		Alert pop = driver.switchTo().alert();
		Thread.sleep(3000);
		pop.accept();
	}

	public void promptPopupJs(By locator1, String value) throws InterruptedException {
		getElement(locator1).click();
		Alert pop = driver.switchTo().alert();
		pop.sendKeys(value);
		pop.accept();
	}

	public void popup_FileUpload(By locator1, String value) {
		getElement(locator1).sendKeys(value);
	}

	public void iframe(By frame, By locator1, String value) {
		driver.switchTo().frame(getElement(frame));
		getElement(locator1).sendKeys(value);

	}

	public void parent_Frame() {
		driver.switchTo().parentFrame();

	}

	// RElativeLocators Concept -------------

	public WebElement withAboveMethod(By searchEle, By baseEle) {
		return getElement(with(searchEle).above(baseEle));
	}

	public WebElement withBelowMethod(By searchEle, By baseEle) {
		return getElement(with(searchEle).below(baseEle));
	}

	public WebElement withLeftMethod(By searchEle, By baseEle) {
		return getElement(with(searchEle).toLeftOf(baseEle));
	}

	public WebElement withRightMethod(By searchEle, By baseEle) {
		return getElement(with(searchEle).toRightOf(baseEle));
	}

	public WebElement withNearMethod(By searchEle, By baseEle) {
		return getElement(with(searchEle).near(baseEle));
	}

	// ***********WebDriver Wait Concept***********************

	private WebDriverWait waitMethod(int time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		return wait;
	}

	// wait for element presence
	public WebElement waitForElementPresence(By waitEle, int time) {
		return waitMethod(time).until(ExpectedConditions.presenceOfElementLocated(waitEle));
	}

	public WebElement waitForElementVisible(By waitEle, int time) {
		return waitMethod(time).until(ExpectedConditions.visibilityOfElementLocated(waitEle));
	}

	public String waitForURLIs(int time, String urlFraction) {
		try {
			if (waitMethod(time).until(ExpectedConditions.urlContains(urlFraction))) {
				return driver.getCurrentUrl();
			}
		} catch (Exception e) {
			System.out.println("PLS PASS RIGHT PASSWORD with repective TIME OUT  " + time);
		}
		return null;

	}

	public String waitForTitleContains(int time, String titleFraction) {
		try {
			if (waitMethod(time).until(ExpectedConditions.titleContains(titleFraction))) {
				return driver.getTitle();
			}

		} catch (Exception e) {
			System.out.println("Title Fractions Not FOund Within" + time);
		}
		return null;
	}
	public  Alert waitForJSAlert(int time) {
		return waitMethod(time).until(ExpectedConditions.alertIsPresent());
	}
	public  void wait_AcceptJsAlert(int time) {
		System.out.println(wait_GetTextJsAlert(3));
		waitForJSAlert(time).accept();
	}
	public  void wait_DismissJsAlert(int time) {
		System.out.println(wait_GetTextJsAlert(3));
		waitForJSAlert(time).dismiss();
	}
	public  String wait_GetTextJsAlert(int time) {
		return waitForJSAlert(time).getText();
	}
	public  void wait_SendKeysJsAlert(int time, String value) {
		System.out.println(wait_GetTextJsAlert(3));
		waitForJSAlert(time).sendKeys(value);
		wait_AcceptJsAlert(time);
	}
	public void clickWhenReady(By locator, int timeOut) {
		waitMethod(timeOut).until(ExpectedConditions.elementToBeClickable(locator));
	}
	public List<WebElement> waitForElementsVisible(By locator, int timeOut) {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
//		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		return waitMethod(timeOut).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	public List<WebElement> waitForElementsPresence(By locator, int timeOut) {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
//		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		return waitMethod(timeOut).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}
	
	public  void waitForFrameAndSwitchToIt(By frameLocator, int timeOut) {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
//		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
		waitMethod(timeOut).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}
	
	
	}
