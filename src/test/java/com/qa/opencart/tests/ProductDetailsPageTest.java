package com.qa.opencart.tests;

import org.testng.Assert;


import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.Errors;

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
	
	
	@Test (priority=1)
	
	public void ProductListPageTitleTest()
	{
		productListPage=accPage.doSearch("iMac");
		productDetailsPage=productListPage.selectProductFromResults("iMac");
		String header=productDetailsPage.getProductHeaderText();
		Assert.assertEquals(header, Constants.PRODUCT_DETTAILS_HEADER);
	}
	@Description("product details page")
	@Severity(SeverityLevel.CRITICAL)
	@Test (priority=2)
	public void productInfoDataTest() {
		productListPage = accPage.doSearch("iMac");
		productDetailsPage = productListPage.selectProductFromResults("iMac");
		Map<String, String> ProductInfoMap = productDetailsPage.getProductMetaData();
		System.out.println(ProductInfoMap.get("Name"));
		softAssert.assertTrue(ProductInfoMap.get("Name").equals("iMac"));
		softAssert.assertTrue(ProductInfoMap.get("Brand").equals("Apple"));
		softAssert.assertTrue(ProductInfoMap.get("price").equals("$100.00"));
		softAssert.assertAll();
		
		//Assert.assertEquals(ProductInfoMap.get("Name"),"MacBook Pro");
		
	}
	@Description("Add to cart ")
	@Severity(SeverityLevel.BLOCKER)
	
	@Test (priority=3,enabled=false)
	public void addCartProductTest()
	{
		productListPage = accPage.doSearch("iMac");
		productDetailsPage = productListPage.selectProductFromResults("iMac");
		productDetailsPage.selectQty("1");
		productDetailsPage.addToCart();
		productDetailsPage.getSuccessMeg();
		
		Assert.assertEquals(productDetailsPage.getSuccessMeg(),Constants.SUCCESS_ADDCART_MESSAGE,Errors.ADDCART_SUCCESS_ERROR);
	}
	

	

}
