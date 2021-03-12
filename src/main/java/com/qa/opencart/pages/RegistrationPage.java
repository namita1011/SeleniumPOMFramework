package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementsUtils;

public class RegistrationPage {
	
	ElementsUtils elemutil;
	
	private By firstName=By.id("input-firstname");
	private By lastname=By.id("input-lastname");
	private By email=By.id("input-email");
	private By telephone=By.id("input-telephone");
	private By password=By.id("input-password");
	private By confirmPwd=By.id("input-confirm");
	private By subscribe_yes=By.xpath("(//label[@class='radio-inline'])[position()=1]/input");
	private By subscribe_no=By.xpath("(//label[@class='radio-inline']/input)[2]");

	private By checkbox=By.name("agree");
	private By continueBtn=By.xpath("//input[@type='submit'and @value='Continue']");
	private By success_msg=By.xpath("//div[@id='content']/h1");
	
	private By logout=By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	
	
	public RegistrationPage(WebDriver driver)
	{
		elemutil=new ElementsUtils(driver);
	}
	
	public boolean accountRegistartion(String firstName,String lastname,String email,
			String telephone,String password,String subscribe )
	
	{
		elemutil.doSend(this.firstName,firstName);
		elemutil.doSend(this.lastname, lastname);
		elemutil.doSend(this.email, email);
		elemutil.doSend(this.telephone, telephone);
		elemutil.doSend(this.password, password);
		elemutil.doSend(this.confirmPwd, password);
		
		if (subscribe.equalsIgnoreCase(("Yes")))
		 {
			elemutil.doClickLinks(subscribe_yes, subscribe);
		}
		else
		{
			elemutil.doClickLinks(subscribe_no, subscribe);
		}
		elemutil.doClick(checkbox);
		elemutil.doClick(continueBtn);
		
		elemutil.waitForVisiblilityOfElement(success_msg, 10);
		String actualMessage=elemutil.getElementText(success_msg);
		
		if (actualMessage.contains(Constants.REGISTARTION_SUCCESS_MSG))
		{
			elemutil.doClick(logout);
			elemutil.doClick(registerLink);
			return true;
		}
		else 
		{
			return false;
		}
		
		
	}
	
}
