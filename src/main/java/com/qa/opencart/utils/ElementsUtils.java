package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementsUtils {
	
	private WebDriver driver;
	private Actions action;
	private JavaScriptUtils jsUtil;
	
	//constructor is used to initialize the driver
	
	public ElementsUtils(WebDriver driver)
	{
		this.driver=driver;
		action=new Actions(this.driver);
		jsUtil=new JavaScriptUtils(this.driver);
	}
	
	/**
	 * This method is used to get webelement
	 * @param locator
	 * @return
	 */
	public  WebElement getElement(By locator)
	{
		
        WebElement element= driver.findElement(locator)	;
        
        if (Boolean.parseBoolean(DriverFactory.highlight))
        {
        	jsUtil.flash(element);
        }
		return element;
        
	}
	
	/**
	 * This methos is used to get title
	 */
	
	public String getTitle()
	{
		return driver.getTitle();
	}
	
	
	public String getElementText(By locator)
	{
		return getElement(locator).getText();
	}
	
	/**
	 * This method is used to check if webelement is displayed
	 */
	
	public boolean isDisplayed(By locator)
	{
		return driver.findElement(locator).isDisplayed();
	}
	
	
	/**
	 * This method is used to get list of webelements
	 * @param locator
	 * @return
	 */
	
	public  List<WebElement> getElements(By locator)
	{
		return driver.findElements(locator);
	}
	/**
	 * This method is used to send data in text field
	 * @param locator
	 * @param value
	 */
	
	public  void doSend(By locator,String value)
	{
		WebElement ele=getElement(locator);
		ele.clear();
		ele.sendKeys(value);
	}
	/**
	 * This method is used to click any webelement
	 * @param locator
	 */
	
	public void doClick(By locator) 
	{
		driver.findElement(locator).click();
	}
	
	/**
	 * This method is used to check wheather checkbox is checked or nor
	 * @param locator
	 * @return
	 */
	public boolean isChecked(By locator)

	{
		return driver.findElement(locator).isSelected();
	}
	/**
	 * This method is used to click on links based on text
	 * @param locator
	 * @param value
	 */

	
	public  void doClickLinks(By locator,String value)
	 {
		List <WebElement>linkslist=getElements(locator);
		System.out.println(linkslist.size());
		for(WebElement e:linkslist)
		{
			String text=e.getText();
			System.out.println(text);
			
			if (text.equals(value))
			{
				e.click();
				break;
			}
		}
	 }
	
	
	/***********DrodpDown selection ***************
	/**This is method is used to select value from dropdown based on index
	 * 
	 */
	
	public  void doSelectByIndex(By locator ,int index)
	{
		Select select=new Select(getElement(locator));
		select.selectByIndex(index);
	}
	
	/**This method is used to select value from dropdown based on value
	 * 
	 */
	
	public  void doSelectByValue(By locator ,String value)
	{
		Select select=new Select(getElement(locator));
		select.selectByValue(value);
	}
	/**This method is used to select value from dropdown based on visible text
	 * 
	 */
	
	public  void doSelectByVisibleText(By locator ,String visibleText)
	{
		Select select=new Select(getElement(locator));
		select.selectByVisibleText(visibleText);
	}
	/**This method is used to select all values from dropdown
	 * 
	 */
	
	public  List<String> getDropDownOptionsList(By locator)
	{
		Select select=new Select(getElement(locator));
		List<String>AllOptionsList=new ArrayList<String>();
		List<WebElement>optionsList=select.getOptions();
		
		for (WebElement e:optionsList)
		{
			 AllOptionsList.add(e.getText());
		}
		
		return AllOptionsList;
		
	}
	
	public  void doSelectDropDownWithOutSelect(By locator,String value )
	{
		List<WebElement>OptionList=driver.findElements(locator);
		System.out.println("Total link" +OptionList.size());
		
		for (WebElement e:OptionList)
		{
			String text=e.getText();
			System.out.println(text);
			if (text.equals(value))
			{
				e.click();
				break;
			}
		}
	}
	
	/*********MoveToElement****************/
	//Single level menu
	public  void doMovetoElement(By locator)
	{
		Actions action=new Actions(driver);action.moveToElement(getElement(locator)).perform();
	}
	
	//2-level menu
		public  void doMovetoElement(By locator1,By locator2)
		{
			
			action.moveToElement(getElement(locator1)).perform();
			action.moveToElement(getElement(locator2)).perform();
		}

		//3-level with click
		
		public  void doMovetoElement(By locator1,By locator2,By locator3)
		{
			
			action.moveToElement(getElement(locator1)).perform();
			action.moveToElement(getElement(locator2)).perform();
			getElement(locator3).click();
		}

		
		public  void doActionSendKeys(By locator,String value)
		{
			Actions action=new Actions(driver);
			
			action.sendKeys(getElement(locator),value).perform();;
			
		}
		
		public  void doActionClick(By locator)
		{
			Actions action=new Actions(driver);
			
			action.click(getElement(locator)).perform();
			
		}
	
		
		/*************CalendarUtil***********************/
		
		public  void selectDate(String tagname,String date)
		{
			driver.findElement(By.xpath("//"+tagname+"[text()='"+date+"']")).click();
		}
		
		
		
		public  void selectDate(By locator,String date)
		{
			List<WebElement>daylist=driver.findElements(locator);
			
			boolean flag=false;
			for (WebElement e:daylist)
			{
				if (e.getText().equals(date))
				{
					System.out.println(date);
					flag=true;
					e.click();
					break;
				}
			}
			
			if(!flag)
			{
				System.out.println("Incorrect date,Please enter correct date");
			}
		}

		
		/***********wait*****************/
		
		public  WebElement waitForPresenceOfElement(By locator,int time )
		{
			WebDriverWait wait=new WebDriverWait(driver, time);
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			
		}
		
		
		public  Alert waitAlertPresent(int time)
		{
			WebDriverWait wait=new WebDriverWait(driver,time);
			return wait.until(ExpectedConditions.alertIsPresent());
		}
		
		public  String getAlertText(int time)
		{
			Alert alert=waitAlertPresent(time);
			return alert.getText();
		}
		
		public  void acceptAlert(int time)

		{
			waitAlertPresent(time).accept();
			
		}
		
		public  void dismissAlert(int time)
		{
			waitAlertPresent(time).dismiss();
		}
		
		public  String waitForTitles(int time,String title)
		{
			WebDriverWait wait=new WebDriverWait(driver,time);
			wait.until(ExpectedConditions.titleIs(title));
			return driver.getTitle();
		}
		
		public  String waitForTitleContains(int time,String title)
		{
			WebDriverWait wait=new WebDriverWait(driver,time);
			wait.until(ExpectedConditions.titleContains(title));
			return driver.getTitle();
		}
		
		public  Boolean waitForTitle(int time,String title)
		{
			WebDriverWait wait=new WebDriverWait(driver,time);
			return wait.until(ExpectedConditions.titleIs(title));
			
		}
		
		
		/**
		 * An expectation for checking that an element, known to be present on the DOM
		 * of a page, is visible. Visibility means that the element is not only
		 * displayed but also has a height and width that is greater than 0.
		 * 
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		public WebElement waitForVisiblilityOfElement(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			return wait.until(ExpectedConditions.visibilityOf(getElement(locator)));
		}

		public List<WebElement> waitForVisiblilityOfElements(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		}

		public void printElementsTexts(By locator, int timeOut) {
			waitForVisiblilityOfElements(locator, timeOut).stream().forEach(ele -> System.out.println(ele.getText()));
		}
		
		
		/**
		 * An expectation for checking an element is visible and enabled such that you
		 * can click it.
		 * 
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		public WebElement waitForElementToBeClickable(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			return wait.until(ExpectedConditions.elementToBeClickable(locator));
		}

		public void clickWhenReady(By locator, int timeOut) {
			waitForElementToBeClickable(locator, timeOut).click();
		}

		public String getElementAttribute(By locator, int timeOut, String name) {
			return waitForElementToBeClickable(locator, timeOut).getAttribute(name);
		}

		public boolean waitForElementTextToBe(By locator, int timeOut, String value) {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			return wait.until(ExpectedConditions.textToBe(locator, value));
		}
		

		public boolean waitForUrlToBe(int timeOut, String urlValue) {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			return wait.until(ExpectedConditions.urlContains(urlValue));
		}

		public boolean waitForExactUrlToBe(int timeOut, String urlValue) {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			return wait.until(ExpectedConditions.urlToBe(urlValue));
		}
		
		/**
		 * An expectation for checking if the given element is selected.
		 * 
		 * @param timeOut
		 * @param urlValue
		 * @return
		 */
		public boolean waitForElementToBeSelected(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			return wait.until(ExpectedConditions.elementToBeSelected(locator));
		}

		public void waitForFrameToBeAvailable(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
		}
		
		public void waitForFrameToBeAvailable(String nameOrId, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrId));
		}

		public void waitForFrameToBeAvailable(int frameIndex, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
		}

		public WebElement waitForElementWithFluentWait(By locator, int timeOut, int pollingTime) {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					.withTimeout(Duration.ofSeconds(timeOut))
					.pollingEvery(Duration.ofSeconds(pollingTime))
					.ignoring(StaleElementReferenceException.class)
					.ignoring(NoSuchElementException.class);

			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

		}

		
}
