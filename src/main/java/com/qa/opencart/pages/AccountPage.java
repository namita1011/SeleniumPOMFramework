package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementsUtils;

public class AccountPage {
	
	WebDriver driver;
	
	ElementsUtils elemutil;
	
	private By logo=By.xpath("//div[@id='logo']//a");
	private By headers =By.cssSelector("#content h2");
	//private By MyAccountList=By.xpath("//input[@name='search']");
	private By search=By.xpath("//input[@name='search']");
	private By searchbtn=By.cssSelector("div#search button");
	//private By linksEachSection=By.xpath("//h2[text()='My Account']/following-sibling::ul[1]/li");
	
	public AccountPage(WebDriver driver)
	{
		this.driver=driver;
		elemutil=new ElementsUtils(driver);
	}
	
	public String AccountPageTitle()
	{
		return elemutil.waitForTitles(10, Constants.ACCOUNT_PAGE_TITLE);
	}
	
	public boolean Logo()
	{
		return elemutil.isDisplayed(logo);
	}
	
	public int getAccountPageHeaderCount()
	{
		return elemutil.getElements(headers).size();
	}
	
	
	
	public List<String> getAccountPagerHeaderList()
	{
		List<WebElement>accHeaderList=elemutil.getElements(headers);
		List<String>headerActualList=new ArrayList<String>();
		for (WebElement e :accHeaderList)
		{
			headerActualList.add(e.getText());
		
		}
		return headerActualList;
	}
	
	
	public List<String> linksforEachSection(String header) 
	{
		
		List<WebElement>linksList=driver.findElements(By.xpath("//h2[text()='"+header+"']/following-sibling::ul[1]/li"));
		List<String>ActualList=new ArrayList<String>();
		for (WebElement e :linksList)
		{
			ActualList.add(e.getText());
		}
		
		System.out.println(ActualList);
		return ActualList;
		
	}
	
	
	
	public ProductListPage doSearch(String productName)
	{
		elemutil.doSend(search, productName);
		elemutil.doClick(searchbtn);
		return new ProductListPage(driver);
	}
	
}
