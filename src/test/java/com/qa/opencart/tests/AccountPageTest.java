package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
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

@Epic("PI-111 Account page")
@Story("US-121")

public class AccountPageTest extends BaseTest{
SoftAssert softassert= new SoftAssert();

	@BeforeClass
	public void AccountSetup()
	{
		accPage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@Description("Account page title")
	@Severity(SeverityLevel.MINOR)
	
	@Test(priority=1)
	public void accountPageTitleTest()
	{
		String title=accPage.AccountPageTitle();
		Assert.assertEquals(title, Constants.ACCOUNT_PAGE_TITLE);
	}
	
	
	@Description("Logo validation")
	@Severity(SeverityLevel.MINOR)
	
	@Test(priority=2)
	public void isLogoDisplayedTest()
	{
		Assert.assertTrue(accPage.Logo());
	}
	
	
	@Description("headerCountTest")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=3)
	public void headerCountTest()
	{
		int totalHeader=accPage.getAccountPageHeaderCount();
		Assert.assertEquals(totalHeader, Constants.TOTAL_HEADERS_COUNTS);
	}
	
	@Description("headerListTest")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=4)
	public void headerListTest()
	{
		List<String>actualHeaderList=accPage.getAccountPagerHeaderList();
		
		Assert.assertEquals(actualHeaderList, Constants.expHeaderList());
	}
	
	/*@Test(priority=5,enabled=false)
	public void LinksSectionList()
	{
		List<String>acList=accPage.linksforEachSection("My Orders");
		
	
	}*/
	@Description("searchProductTest")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=6)
	public void searchProductTest()
	{
		productList=accPage.doSearch("macbook");
		Assert.assertTrue(productList.getProductsCount()>0,Errors.PRODUCT_COUNT_ERROR);		
	}
	@Description("searchProductTest")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=7)
	public void SelectProductTest()
	{   
		productList=accPage.doSearch("macbook");
		productDetails=productList.selectProductFromResults("MacBook Pro");
		String actualHeader=productDetails.getProductHeaderText();
		softassert.assertEquals(actualHeader, "MacBook Pro",Errors.PRODUCT_HEADER_NOTFOUND_ERROR);
		softassert.assertEquals(productDetails.getProductImages(), Constants.PRODUCT_DETAILS_IMAGES,Errors.PRODUCT_IMAGES_ERROR);
		softassert.assertAll();	
	}
}
