package com.qa.opencart.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
	
	int count=0;
	int retryCount=3;
	
	public boolean retry(ITestResult resut)
	{
		if (count<retryCount)
		{
			count++;
			return true;
		}
		
		return false;
		
	}

}
