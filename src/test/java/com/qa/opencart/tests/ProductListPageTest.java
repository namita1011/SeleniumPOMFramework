package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

public class ProductListPageTest extends BaseTest {
	
	@BeforeClass()
	 public void accountSetUp()
	 {
		accPage=loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password") );
	 }
	
	
	@Test
	
	public void AddToCartTest()
	{
		productListPage=accPage.doSearch("iMac");
		commonPage=productListPage.addTocart("iMac");
		commonPage.getCartDetails();
		Assert.assertEquals(commonPage.getCartDetails(), true, Constants.SUCCESS_CART_IS_NOT_EMPTY);
	}

}
