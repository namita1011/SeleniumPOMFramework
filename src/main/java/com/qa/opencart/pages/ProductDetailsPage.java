package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.qa.opencart.utils.ElementsUtils;

public class ProductDetailsPage {
	
	//private WebDriver driver;
	ElementsUtils elemutil;
	
	private By productDetailsImg=By.cssSelector("ul.thumbnails li");
	private By productTitle=By.cssSelector("div#content h1");
	private By productMetaData=By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By productPrice=By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By quntity=By.xpath("//input[@id='input-quantity']");
	private By addToCart=By.cssSelector(".btn.btn-primary.btn-lg.btn-block");
	private By successMessage=By.cssSelector("div.alert.alert-success.alert-dismissible");

	public ProductDetailsPage(WebDriver driver) {
		
		//this.driver=driver;
		elemutil=new ElementsUtils(driver);
		
	}
	
	public int getProductImages()
	{
	 return elemutil.getElements(productDetailsImg).size();
		
	}
	
	public String getProductHeaderText()
	{
		return elemutil.getElement(productTitle).getText();
		
	}
		
	public HashMap<String, String> getProductMetaData()
	{
		HashMap<String ,String>productInfoMap=new HashMap<String,String>();
		
		productInfoMap.put("Name", getProductHeaderText().trim());
		List<WebElement>productMetaDataList=elemutil.getElements(productMetaData);
		
		for (WebElement e:productMetaDataList)
		{
			String[] metaData=e.getText().split(":");
			String metaKey=metaData[0].trim();
			String metaValue = metaData[1].trim();
			productInfoMap.put(metaKey, metaValue);
		}
		
		List<WebElement>priceList=elemutil.getElements(productPrice);
		
		productInfoMap.put("price", priceList.get(0).getText().trim());
		productInfoMap.put("priceList",priceList.get(1).getText().trim());
		
		return productInfoMap;
			
	}
	
	public void selectQty(String qty)
	{
		 elemutil.doSend(quntity, qty);
		
	}
	public void addToCart()
	{
		elemutil.doClick(addToCart);
	}
	
	public String getSuccessMeg()
	{
		return elemutil.getElement(successMessage).getText();
	}

}
