package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.CommonUtil;
import com.qa.opencart.utils.Errors;
import com.qa.opencart.utils.ExcelSheetUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("PI-1212 Registartion")
@Story("US-45545 ")

public class RegistrationPageTest extends BaseTest{
	
	
	
	@BeforeClass
	public void setUpRegistartion()
	{
		registrationPage=loginpage.NavigateToPage();
	}
	
	
	@DataProvider
	public Object[][] getRegisterData()
	{
		Object data[][]=ExcelSheetUtil.getSheetData("Register");
		
		return data;
	}
	
	@Description("Register with different set of data")
	@Severity(SeverityLevel.NORMAL)
	@Test(dataProvider="getRegisterData")
	public void accountCreationTest(String fname,String lname,String telephone ,String password,String subscribe)
	{
		Assert.assertTrue(registrationPage.accountRegistartion(fname,
		lname, CommonUtil.getRandomEmail(), telephone ,password,subscribe),Errors.REGSTER_ERROR);
	}

}
