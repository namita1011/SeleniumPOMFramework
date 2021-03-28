package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.CommonFunctionality;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductDetailsPage;
import com.qa.opencart.pages.ProductListPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.tests.ProductListPageTest;

public class BaseTest {
	private WebDriver driver;
	public Properties prop;
	DriverFactory df;
	
	public LoginPage loginpage;
	public AccountPage accPage;
	public ProductListPage productListPage;
	public ProductDetailsPage productDetailsPage;
	public RegistrationPage registrationPage;
	public ProductListPageTest productlistPage;
	public CommonFunctionality commonPage;
	
	@Parameters({"browser","version"})
	@BeforeTest (alwaysRun = true)
	public void setUp(@Optional String browserName,@Optional String browserVersion)
	{
		System.out.println(browserName + " "+browserVersion );
		df=new DriverFactory();
		prop=df.inti_prop();
		
		//Read the properties from property file
		String browser=prop.getProperty("browser");
		//Read the properties from environment when pass browser from man command like mvn clean install -Dbrowser='Chroe'
		String browserEnv=System.getProperty("browser");
		/*
		 * if browser name null from xml parameter then use set browser from properties file
		 */
		if (browserName!=null)
		{
			browser=browserName;
		}
		
		/*
		 * if browser name null from xml parameter and user pass the browser maven command then use enviornment parameter
		 */
		
		if(browserEnv!=null)
		{
			browser=browserEnv;
		}
		
		driver=df.inti_driver(browser,browserVersion);
		loginpage=new LoginPage(driver);
		
	}
	
	
	/*@AfterTest
	public void tearDown()
	{
		driver.quit();
	}*/

}
