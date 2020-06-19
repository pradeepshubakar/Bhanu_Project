package com.Sample.CompanyName.ProjectName.testScripts.LoginPage;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.Sample.CompanyName.ProjectName.helper.assertion.AssertionHelper;
import com.Sample.CompanyName.ProjectName.helper.browserConfiguration.config.ObjectReader;
import com.Sample.CompanyName.ProjectName.helper.logger.LoggerHelper;
import com.Sample.CompanyName.ProjectName.pageObjects.LoginPage;
import com.Sample.CompanyName.ProjectName.testbase.TestBase;

public class LoginTest extends TestBase {

	private Logger log = LoggerHelper.getLogger(LoginTest.class);
	
	@Test(description="Login test with valid credentials")
	public void testLoginToApplication() {
		getApplicationUrl(ObjectReader.reader.getUrl());
		
		LoginPage loginPage = new LoginPage(driver);
		
		loginPage.loginToApplication(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());
		boolean status = loginPage.verifySuccessLoginMsg();
		AssertionHelper.updateTestStatus(status);
	}
}
