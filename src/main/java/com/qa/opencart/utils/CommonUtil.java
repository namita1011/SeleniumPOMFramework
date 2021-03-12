package com.qa.opencart.utils;

import java.util.Random;

public class CommonUtil {
	
	
	public static String getRandomEmail()
	{
		Random randomGenerator=new Random();
		String email="testautomation"+randomGenerator.nextInt(200)+"@gmail.com";
		return email;
	}
	

}
