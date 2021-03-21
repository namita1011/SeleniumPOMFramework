package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	// WebDriver driver;
	 Properties prop;
	 OptionsManager optionsManger;
	 
	 public static String highlight; 
	 
	 DesiredCapabilities cap;
	 
	 public static  ThreadLocal<WebDriver>tlDriver=new ThreadLocal<>();
	
	public WebDriver inti_driver(String browserName,String browserVersion)
	{
		//String browserName=prop.getProperty("browser").trim();
		highlight=prop.getProperty("highlight").trim();
		optionsManger=new OptionsManager(prop);
		
		System.out.println(browserName);
		
		if (browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			
			if (Boolean.parseBoolean(prop.getProperty("remote")))
			{
				inti_remoteDriver(browserName,browserVersion);
			}
			
			else
			{
				tlDriver.set(new ChromeDriver(optionsManger.getChromeOptions()));
			}
			
		}
		
		else if (browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			if (Boolean.parseBoolean(prop.getProperty("remote")))
			{
				inti_remoteDriver(browserName,browserVersion);
			}
			else {
			tlDriver.set(new FirefoxDriver(optionsManger.getFireFoxoptions()));
		}
		}
		
		else if (browserName.equalsIgnoreCase("safari"))
		{
			
			//driver=new SafariDriver();
			tlDriver.set(new SafariDriver());
		}
		
		else {
			
			System.out.println("Browser not found.Please enter correct browser ...."+ browserName);
		}
		
		getDriver().manage().window().fullscreen();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url").trim());
		return getDriver();
	}
	
	/**
	 * this method define desired capbility and intialize driver with given capbilites .this call on selenium grid 
	 * @param browserName
	 */
     private void inti_remoteDriver(String browserName,String browserVersion) {
		
    	 if (browserName.equals("chrome"))
    	 {
    	  cap= DesiredCapabilities.chrome();
    	 cap.setCapability(ChromeOptions.CAPABILITY, optionsManger.getChromeOptions());
    	 cap.setCapability("browserName", browserName.toLowerCase());
    	 cap.setCapability("browserVersion", browserVersion);
    	 cap.setCapability("enableVNC", true);
    	 try {
			tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), cap));
		   } catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 }
    	 else if (browserName.equals("firefox"))
    	 {
    		 
    	    cap=DesiredCapabilities.firefox();
    	    
    	    cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, optionsManger.getFireFoxoptions());
    	    cap.setCapability("browserName", browserName.toLowerCase());
       	    cap.setCapability("browserVersion", browserVersion);
       	    cap.setCapability("enableVNC", true);
    	    try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), cap));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	 
    		 }
    	 
    	 
	}
/*
 * This method is return driver of ThreadlocalDriver	
 */
	
	public static synchronized WebDriver getDriver()
	{
		return tlDriver.get();
	}
	
	public Properties inti_prop()
	{
		 prop=new Properties();
		 FileInputStream file=null;
		 //read environment from command line/
		 String env=System.getProperty("env");
		 if (env==null)
		 {
			 try {
				file=new FileInputStream("./src/test/resources/config/config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 else
			 
		 {
			 try {
			 switch (env) {
			 
			case "qa":
				
				file=new FileInputStream("./src/test/resources/config/config_qa.properties");
				break;
			case "stage":
				file=new FileInputStream("./src/test/resources/config/config_staging.properties");
				break;
			case "dev":
				file=new FileInputStream("./src/test/resources/config/config_dev.properties");
				break;

			default:
				System.out.println("enviornment is not available");
				break;
			}
		 }
			 catch(FileNotFoundException e){
				 e.printStackTrace();
				 }
			 }
		 
		try {
			prop.load(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop;
	}

	public String getScreenshot() 
	{
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		//File srcFile = new File(src);

		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}
}

