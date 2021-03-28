package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("PI-101 login functionality")
@Story("Us-100")
public class LoginPageTest extends BaseTest {

	@Description("Login title validaton")
	@Severity(SeverityLevel.MINOR)
	
	
	@Test (priority=1,groups={"smoke"})
	public void LoginPageTitleTest()
	{
		String title=loginpage.getLoginPageTitle();
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	@Description("forgot password link validaton")
	@Severity(SeverityLevel.MINOR)
	@Test (priority=2,groups={"sanity"})
	public void ForgotPwdLinkDisplayTest()
	{
		Assert.assertTrue(loginpage.isForgotpwdLinkExist());
		
	}
	@Description("Login validaton")
	@Severity(SeverityLevel.CRITICAL)
	@Test (priority=3,enabled=true)
	public void doLoginTest()
	{
	
		loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		
	}

}
