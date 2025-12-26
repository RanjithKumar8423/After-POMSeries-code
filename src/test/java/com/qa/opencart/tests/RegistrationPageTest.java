package com.qa.opencart.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.StringUtils;

public class RegistrationPageTest extends BaseTest {

	
	@BeforeClass
	public void regSetup() {
		registrationPage = loginpage.navigateToRegister();
	}
	@DataProvider
	public  Object[] []  getuserRegData() {
		return new Object[] [] {
			{"macbook","user","3999949","macbook@1333","yes", StringUtils.getRandomEmail("macbook")},
			{"imac","user","3999949","imac@1333","yes", StringUtils.getRandomEmail("imac")},
			{"samsung","user","3999949","samsung@1333","yes", StringUtils.getRandomEmail("samsung")},
			{"Ranjith","kumar","3999949","Ranjith@1333","no", StringUtils.getRandomEmail("Ranjith")}
		};
	}
	
	@DataProvider
	public  Object[] []  getuserRegDataFromExcel() {
	return ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
	}
	@Test(dataProvider = "getuserRegDataFromExcel")
	public void userRegTest(String firstname,String lastName, String mobile, String password, String subscribe) {
		registrationPage.userRegister(firstname, lastName, mobile, password, subscribe, StringUtils.getRandomEmail("imac"));
	}
	
//	
//	@Test(dataProvider = "getuserRegData")
//	public void userRegTest(String firstname,String lastName, String mobile, String password, String subscribe, String email) {
//		registrationPage.userRegister(firstname, lastName, mobile, password, subscribe, email);
//	}
	
}
