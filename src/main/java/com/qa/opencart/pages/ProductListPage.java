package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementsUtils;

public class ProductListPage{
	
	WebDriver driver;
	ElementsUtils elemutil;
	
	By products=By.cssSelector("div.product-thumb img");
	By resultItems=By.cssSelector("div.product-thumb h4 a");
	By addToCartBtn=By.xpath("//span[text()='Add to Cart']");
	
	public ProductListPage(WebDriver driver)
	
	{
	    this.driver=driver;
		elemutil=new ElementsUtils(driver);
	}
	
	public int getProductsCount()
	{
		return elemutil.getElements(products).size();
	}
	
	
	public ProductDetailsPage selectProductFromResults(String productName)
	{
		List<WebElement>resultItemList=elemutil.getElements(resultItems);
		System.out.println("Total number of items displayed for :"+productName + ":"+resultItemList);
		
		for (WebElement e:resultItemList)
		{
			if (e.getText().equals(productName))
			{
			System.out.println(e.getText());
			e.click();
			break;
			}
		}
		
		return new ProductDetailsPage(driver);
	}
	
	

}
