package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	// WebDriver driver;
	 Properties prop;
	 OptionsManager optionsManger;
	 
	 public static String highlight; 
	 
	 public static  ThreadLocal<WebDriver>tlDriver=new ThreadLocal<>();
	
	public WebDriver inti_driver(Properties prop)
	{
		String browserName=prop.getProperty("browser").trim();
		highlight=prop.getProperty("highlight").trim();
		optionsManger=new OptionsManager(prop);
		
		System.out.println(browserName);
		
		if (browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			//driver=new ChromeDriver(optionsManger.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManger.getChromeOptions()));
			
		}
		
		else if (browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			//driver=new FirefoxDriver(optionsManger.getFireFoxoptions());
			tlDriver.set(new FirefoxDriver(optionsManger.getFireFoxoptions()));
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
		
		try {
			FileInputStream file=new FileInputStream("./src/test/resources/config/config.properties");
			
			prop.load(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop;
	}

	public String getScreenshot() 
	{
		File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path=System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".png";
		File destination=new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return path;
	}
}

