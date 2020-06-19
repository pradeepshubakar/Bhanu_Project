package com.Sample.CompanyName.ProjectName.pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Sample.CompanyName.ProjectName.helper.assertion.VerificationHelpler;
import com.Sample.CompanyName.ProjectName.helper.browserConfiguration.config.ObjectReader;
import com.Sample.CompanyName.ProjectName.helper.javaScript.JavaScriptHelper;
import com.Sample.CompanyName.ProjectName.helper.logger.LoggerHelper;
import com.Sample.CompanyName.ProjectName.helper.wait.WaitHelper;
import com.Sample.CompanyName.ProjectName.testbase.TestBase;
import com.aventstack.extentreports.Status;

public class LoginPage {

	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(LoginPage.class);
	
	WaitHelper waitHelper;
	
	@FindBy(xpath = "//a[contains(.,'Sign in')]")
	WebElement signIn;
	
	@FindBy(xpath = "//input[@id='email']")
	WebElement emailAddress;
	
	@FindBy(xpath = "//input[@id='passwd']")
	WebElement password;
	
	@FindBy(xpath = "//button[@id='SubmitLogin']")
	WebElement submitLogin;
	
	
	@FindBy(xpath = "//*[@id='center_column']/p")
	WebElement successMsgObject;
	
	@FindBy(xpath = "//*[@id='email_create']")
	WebElement registrationEmailAddress;
	
	@FindBy(xpath = "//button[@id='SubmitCreate']")
	WebElement createAnAccount;
	
	@FindBy(xpath = "//h1[.='Authentication']")
	WebElement authentication;

	@FindBy(xpath = "//p[contains(.,'Please enter ')]")
	//or //form[@id='create-account_form']/div/p
	WebElement createAnAccountMessage;
	
	@FindBy(xpath = "//header[@id='header']/div[2]/div/div/nav/div[2]/a")
	WebElement logOut;

	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.WaitForElementClickable(signIn, ObjectReader.reader.getExplicitWait());
		new TestBase().getNavigationScreen(driver);
		TestBase.logExtentReport("LoginPage Object created..");
	}
	
	public void clickOnSignInLink() {
		log.info("clicked on sign in link...");
		//TestBase.test.log(Status.INFO, "clicked on sign in link...");//instead of this we created one method and calling easily
		logExtentReport("clicked on sign in link...");
		signIn.click();
	}
	
	public void enterEmailAddress(String emailAddress) {
		log.info("entering email address.."+emailAddress);
		logExtentReport("entering email address.."+emailAddress);
		this.emailAddress.sendKeys(emailAddress);
	}
	
	public void enterPassword(String password) {
		log.info("entering password.. "+password);
		//logExtentReport("entering password.. "+emailAddress);
		TestBase.logExtentReport("entering password.. "+password);
		this.password.sendKeys(password);
	}
	
	public HomePage clickOnSubmitButton() {
		log.info("clicking on submit button..");
		logExtentReport("clicking on submit button..");
		new JavaScriptHelper(driver).scrollDownVertically();
		submitLogin.click();
		return new HomePage(driver);
	}
	
	public boolean verifySuccessLoginMsg() {
		return new VerificationHelpler(driver).isDisplayed(successMsgObject);
	}
	
	public void enterRegistrationEmail() {
		String email = System.currentTimeMillis()+"@gmail.com";
		log.info("entering registration email.."+email);
		logExtentReport("entering registration email.."+email);
		registrationEmailAddress.sendKeys(email);
	}
	
	public RegistrationPage clickOnCreateAnAccount() {
		createAnAccount.click();
		return new RegistrationPage(driver);
	}
	
	public void loginToApplication(String emailAddress, String password) {
		clickOnSignInLink();
		enterEmailAddress(emailAddress);
		enterPassword(password);
		clickOnSubmitButton();
	}
	
	public void logExtentReport(String s1) {
		TestBase.test.log(Status.INFO, s1);
	}
	
	public void clickOnlogOut() {
		logOut.click();
		waitHelper.waitForElement(signIn, ObjectReader.reader.getExplicitWait());
	}
}
