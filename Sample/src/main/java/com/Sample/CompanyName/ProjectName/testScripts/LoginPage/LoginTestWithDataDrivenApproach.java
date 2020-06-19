package com.Sample.CompanyName.ProjectName.testScripts.LoginPage;

import org.apache.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Sample.CompanyName.ProjectName.helper.assertion.AssertionHelper;
import com.Sample.CompanyName.ProjectName.helper.browserConfiguration.config.ObjectReader;
import com.Sample.CompanyName.ProjectName.helper.logger.LoggerHelper;
import com.Sample.CompanyName.ProjectName.pageObjects.LoginPage;
import com.Sample.CompanyName.ProjectName.pageObjects.NavigationMenu;
import com.Sample.CompanyName.ProjectName.testbase.TestBase;

public class LoginTestWithDataDrivenApproach extends TestBase{
	
	private Logger log = LoggerHelper.getLogger(LoginTestWithDataDrivenApproach.class);
	
	LoginPage login;
	NavigationMenu navigationMenu;
	
	//dataDriven test not possible without this dataProvider
	@DataProvider(name = "testData")
	public Object[][] testData(){
		Object[][] data = getExcelData("testData.xlsx", "LoginData");
		return data;
	}
	

	@BeforeClass
	public void beforeClass() {
		getApplicationUrl(ObjectReader.reader.getUrl());
		login = new LoginPage(driver);
		
	}



	@Test(dataProvider="testData")
	//if we have dataProvider have 3 columns we should provide 3 arguments else we get error.
	public void loginTest(String userName, String password, String runMode) {
		if (runMode.equalsIgnoreCase("n")) {
			throw new SkipException("Run mode this set of data is marked N ");
		}
		login.loginToApplication(userName, password);
		boolean status = login.verifySuccessLoginMsg();
		AssertionHelper.updateTestStatus(status);
		login.clickOnlogOut();
	}
}
