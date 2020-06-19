package com.Sample.CompanyName.ProjectName.pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.Sample.CompanyName.ProjectName.helper.browserConfiguration.config.ObjectReader;
import com.Sample.CompanyName.ProjectName.helper.logger.LoggerHelper;
import com.Sample.CompanyName.ProjectName.helper.wait.WaitHelper;
import com.Sample.CompanyName.ProjectName.testbase.TestBase;

public class HomePage {

	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(HomePage.class);
	WaitHelper waitHelper;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		//waitHelper.WaitForElementClickable(signin, ObjectReader.reader.getExplicitWait());
		new TestBase().getNavigationScreen(driver);
		
	}
}
