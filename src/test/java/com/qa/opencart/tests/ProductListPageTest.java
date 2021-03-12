package com.qa.opencart.tests;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Epic("PI-productlist")
@Story("US-1212112")
public class ProductListPageTest extends BaseTest {
	SoftAssert softAssert = new SoftAssert();
	@BeforeClass
	public void AccSetUp() {
		accPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Description("product details page")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void productInfoDataTest() {
		productList = accPage.doSearch("Macbook");
		productDetails = productList.selectProductFromResults("MacBook Pro");
		Map<String, String> actProductInfoMap = productDetails.getProductMetaData();
		softAssert.assertTrue(actProductInfoMap.get("name").equals("MacBook Pro"));
		softAssert.assertTrue(actProductInfoMap.get("Brand").equals("Apple"));
		softAssert.assertTrue(actProductInfoMap.get("price").equals("$2,000.00"));
		softAssert.assertAll();
		
	}

	

}
