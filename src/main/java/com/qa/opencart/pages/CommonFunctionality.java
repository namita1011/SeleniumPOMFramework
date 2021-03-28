package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementsUtils;

public class CommonFunctionality {
	
	private ElementsUtils elemutil;
	
	
	By shoppingCartButton=By.xpath("//span[@id='cart-total']");
	
	public CommonFunctionality(WebDriver driver)
	{
		 elemutil=new ElementsUtils(driver);
	}
	
	
	public boolean getCartDetails()
	{
		boolean flag=false;
		String[] carddetails= elemutil.getElementText(shoppingCartButton).split("-");

		String qty=carddetails[0];
		String price=carddetails[1];
		Integer.parseInt(price);
		
		if (Integer.parseInt(price)>0)
		{
			flag=true;
		}
		
		else
		{
			flag=false;
		}
		
		return flag;
		
	}
	

}
