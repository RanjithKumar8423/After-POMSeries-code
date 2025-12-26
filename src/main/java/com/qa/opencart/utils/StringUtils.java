package com.qa.opencart.utils;

public class StringUtils {

	
	public static String getRandomEmail(String email) {
		String emailid=email+System.currentTimeMillis()+"@gmail.com";
		return emailid;
	}
	
}
