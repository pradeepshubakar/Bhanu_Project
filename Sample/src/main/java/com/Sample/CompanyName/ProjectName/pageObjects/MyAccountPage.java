package com.Sample.CompanyName.ProjectName.pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Sample.CompanyName.ProjectName.helper.assertion.VerificationHelpler;
import com.Sample.CompanyName.ProjectName.helper.browserConfiguration.config.ObjectReader;
import com.Sample.CompanyName.ProjectName.helper.logger.LoggerHelper;
import com.Sample.CompanyName.ProjectName.helper.wait.WaitHelper;
import com.Sample.CompanyName.ProjectName.testbase.TestBase;
import com.aventstack.extentreports.Status;

public class MyAccountPage {

	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(MyAccountPage.class);
	
	WaitHelper waitHelper;
	
	
	@FindBy(xpath = "//div[@id='center_column']/p")
	WebElement yourAccountPageMsg;
	
	@FindBy(xpath = "//span[.='Order history and details']")
	WebElement orderHistoryAndDetails;
	
	@FindBy(xpath = "//span[.='My personal information']")
	WebElement myPersonalInformation;
	
	@FindBy(xpath = "//span[.='My wishlists']")
	WebElement MyWishLists;
	
	@FindBy(xpath = "//div[@id='center_column']/h1")
	WebElement MyAccountTxt;
	

	public MyAccountPage(WebDriver driver) {
		this.driver = driver;	
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(orderHistoryAndDetails, ObjectReader.reader.getExplicitWait());
		//TestBase.test.log(Status.INFO, "MyAccountPage Object created");//both are same
		TestBase.logExtentReport("MyAccountPage Object created");
		new TestBase().getNavigationScreen(driver);
	}
	
	public void clickOnMyWishLists() {
		
		MyWishLists.click();
		log.info("clicked on"+ MyWishLists.getText());
		TestBase.test.log(Status.INFO, "clicked on"+ MyWishLists.getText());
	}
	
	public void clickOnOrderHistoryAndDetails() {
		orderHistoryAndDetails.click();
		log.info("clicked on"+ orderHistoryAndDetails.getText());
		TestBase.logExtentReport("clicked on"+ orderHistoryAndDetails.getText());
	}
	
	
	public void clickOnmyPersonalInformation() {
		myPersonalInformation.click();
		log.info("clicked on"+ myPersonalInformation.getText());
		TestBase.logExtentReport("clicked on"+ myPersonalInformation.getText());
	}
	public boolean verifyMyAccountTxt() {
		return new VerificationHelpler(driver).isDisplayed(MyAccountTxt);
		
	}
	public boolean verifyYourAccountPageMsg() {
		return new VerificationHelpler(driver).isDisplayed(yourAccountPageMsg);
		
	}
}