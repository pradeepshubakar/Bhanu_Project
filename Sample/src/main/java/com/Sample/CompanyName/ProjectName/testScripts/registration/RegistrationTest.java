package com.Sample.CompanyName.ProjectName.testScripts.registration;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.Sample.CompanyName.ProjectName.helper.assertion.AssertionHelper;
import com.Sample.CompanyName.ProjectName.helper.browserConfiguration.config.ObjectReader;
import com.Sample.CompanyName.ProjectName.helper.logger.LoggerHelper;
import com.Sample.CompanyName.ProjectName.pageObjects.LoginPage;
import com.Sample.CompanyName.ProjectName.pageObjects.MyAccountPage;
import com.Sample.CompanyName.ProjectName.pageObjects.RegistrationPage;
import com.Sample.CompanyName.ProjectName.testbase.TestBase;

public class RegistrationTest extends TestBase{

	private Logger log = LoggerHelper.getLogger(RegistrationTest.class);
	LoginPage loginPage;
	RegistrationPage register;
	MyAccountPage myAccountPage;
	
	@Test
	public void testLoginToApplication() {
		//go to application
		getApplicationUrl(ObjectReader.reader.getUrl());
		
		loginPage = new LoginPage(driver);
		loginPage.clickOnSignInLink();
		loginPage.enterRegistrationEmail();
		loginPage.clickOnCreateAnAccount();
		
		//enter registration data
		register = new RegistrationPage(driver);
		register.setMrRadioButton();
		register.setFirstName("firstName");
		register.setLastName("lastName");
		register.setPassword("password");
		register.setAddress("address");
		register.setDay("16");
		register.setMonth("June");
		register.setYear("2020");
		register.setYourAddressFirstName("yourAddressFirstName");
		register.setYourAddressLastName("yourAddressLastName");
		register.setYourAddressCompany("yourAddressCompany");
		register.setYourAddressCity("yourAddressCity");
		register.setYourAddressState("Alaska");
		register.setYourAddressPostCode("99501");
		register.setMobilephone("9845098450");
		register.setAddressAlias("addressAlias");
		register.clickOnRegistration();
		
		myAccountPage = new MyAccountPage(driver);
		boolean status = myAccountPage.verifyYourAccountPageMsg();
		
		AssertionHelper.updateTestStatus(status);
	}
	
	
}
