package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementsUtils;

public class LoginPage {
	//page objects Locators---OR
	
	WebDriver driver;
	ElementsUtils elemutil;
	
	private By username=By.id("input-email");
	private By password=By.id("input-password");
	private By Login=By.xpath("//input[@type='submit']");
	private By forgotpwd=By.xpath("//div[@class='form-group']/a[text()='Forgotten Password']");
	private By register=By.linkText("Register");
	
	//constructor
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
	   elemutil=new ElementsUtils(driver);
	}
	
	//Page actions
	
	public String getLoginPageTitle()
	{
		return elemutil.getTitle();
	}
	
	public boolean isForgotpwdLinkExist()
	{
		return elemutil.isDisplayed(forgotpwd);
	}
	
	public AccountPage doLogin(String un,String pw)
	{
		System.out.println("Login with:"+ un +":"+pw);
		
		elemutil.doSend(username, un);
		elemutil.doSend(password, pw);
		elemutil.doClick(Login);
		
		return new AccountPage(driver);
	}
	
	public RegistrationPage NavigateToPage()
	{
		elemutil.doClick(register);
		return new RegistrationPage(driver);
	}
	
	
	
}
