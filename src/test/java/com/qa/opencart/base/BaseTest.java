package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductDetailsPage;
import com.qa.opencart.pages.ProductListPage;
import com.qa.opencart.pages.RegistrationPage;

public class BaseTest {
	private WebDriver driver;
	public Properties prop;
	DriverFactory df;
	
	public LoginPage loginpage;
	public AccountPage accPage;
	public ProductListPage productList;
	public ProductDetailsPage productDetails;
	public RegistrationPage registration;
	
	@BeforeTest
	public void setUp()
	{
		df=new DriverFactory();
		prop=df.inti_prop();
		driver=df.inti_driver(prop);
		loginpage=new LoginPage(driver);
		
	}
	
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
