package com.qa.opencart.tests;

import static org.testng.Assert.assertEquals;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Epic("PI-productlist")
@Story("US-1212112")
public class ProductDetailsPageTest extends BaseTest {
	SoftAssert softAssert = new SoftAssert();
	@BeforeClass
	public void AccountSetup() {
		accPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@Test
	
	public void ProductListPageTitleTest()
	{
		productListPage=accPage.doSearch("Macbook");
		productDetailsPage=productListPage.selectProductFromResults("MacBook Pro");
		String header=productDetailsPage.getProductHeaderText();
		assertEquals(header,Constants.PRODUCT_DETTAILS_HEADER);
	}
	@Description("product details page")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void productInfoDataTest() {
	/*	productListPage = accPage.doSearch("Macbook");
		productDetailsPage = productListPage.selectProductFromResults("MacBook Pro");*/
		Map<String, String> ProductInfoMap = productDetailsPage.getProductMetaData();
		softAssert.assertTrue(ProductInfoMap.get("name").equals("MacBook Pro"));
		softAssert.assertTrue(ProductInfoMap.get("Brand").equals("Apple"));
		softAssert.assertTrue(ProductInfoMap.get("price    ").equals("$2,000.00"));
		softAssert.assertAll();
		
	}

	

}
