package com.Sample.CompanyName.ProjectName.pageObjects;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.Sample.CompanyName.ProjectName.helper.browserConfiguration.config.ObjectReader;
import com.Sample.CompanyName.ProjectName.helper.logger.LoggerHelper;
import com.Sample.CompanyName.ProjectName.helper.wait.WaitHelper;
import com.Sample.CompanyName.ProjectName.testbase.TestBase;

public class RegistrationPage {

	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(RegistrationPage.class);

	WaitHelper waitHelper;

	@FindBy(xpath = "//input[@id='id_gender1']")
	public WebElement MrRadioButton;

	@FindBy(xpath = "//input[@id='id_gender2']")
	public WebElement MrsRadioButton;

	@FindBy(xpath = "//input[@id='customer_firstname']")
	public WebElement firstName;

	@FindBy(xpath = "//input[@id='customer_lastname']")
	public WebElement lastName;

	@FindBy(xpath = "//input[@id='email']")
	public WebElement emailAddress;

	@FindBy(xpath = "//input[@id='passwd']")
	public WebElement password;

	@FindBy(xpath = "//select[@id='days']")
	public WebElement days;

	@FindBy(xpath = "//select[@id='months']")
	public WebElement months;

	@FindBy(xpath = "//select[@id='years']")
	public WebElement years;
	
	@FindBy(xpath = "//input[@id='firstname']")
	public WebElement yourAddressFirstName;
	
	@FindBy(xpath = "//input[@id='lastname']")
	public WebElement yourAddressLastName;
	
	@FindBy(xpath = "//input[@id='company']")
	public WebElement yourAddressCompany;
	
	@FindBy(xpath = "//input[@id='address1']")
	public WebElement address;
	
	@FindBy(xpath = "//input[@id='address2']")
	public WebElement address2;
	
	@FindBy(xpath = "//input[@id='city']")
	public WebElement yourAddressCity;
	
	@FindBy(xpath = "//select[@id='id_state']")
	public WebElement yourAddressState;

	@FindBy(xpath = "//input[@id='postcode']")
	public WebElement yourAddressPostCode;

	@FindBy(xpath = "//textarea[@id='other']")
	public WebElement additionalInformation;

	@FindBy(xpath = "//input[@id='phone']")
	public WebElement homePhone;
	
	@FindBy(xpath = "//input[@id='phone_mobile']")
	public WebElement mobilephone;
	
	@FindBy(xpath = "//input[@id='alias']")
	public WebElement addressAlias;
	
	@FindBy(xpath = "//button[@id='submitAccount']")
	public WebElement registration;



	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		WaitHelper waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(MrRadioButton, ObjectReader.reader.getExplicitWait());
		TestBase.logExtentReport("RegistrationPage Object created..");

	}

	public void setMrRadioButton() {
		log.info("selecting mr checkbox..");
		TestBase.logExtentReport("selecting mr checkbox..");
		MrRadioButton.click();
	}

	public void setMrsRadioButton() {
		log.info("selecting mrs checkbox..");
		TestBase.logExtentReport("selecting mrs checkbox..");
		MrsRadioButton.click();
	}

	public void setFirstName(String firstName) {
		log.info("entering first name.." + firstName);
		TestBase.logExtentReport("selecting first name.." + firstName);
		this.firstName.sendKeys(firstName);
	}

	public void setLastName(String lastName) {
		log.info("entering last name.." + lastName);
		TestBase.logExtentReport("selecting last name.." + lastName);
		this.lastName.sendKeys(lastName);
	}

	public void setEmailAddress(String emailAddress) {
		log.info("entering email address.." + emailAddress);
		TestBase.logExtentReport("entering email address.." + emailAddress);
		this.emailAddress.sendKeys(emailAddress);
	}

	public void setPassword(String password) {
		log.info("entering password.." + password);
		TestBase.logExtentReport("selecting password.." + password);
		this.password.sendKeys(password);
	}

	public void setDay(String day) {
		List<WebElement> days = driver.findElements(By.xpath("//select[@id='days']/option"));
		Iterator<WebElement> itr = days.iterator();
		while (itr.hasNext()) {
			WebElement c = itr.next();
			String text = c.getText().trim().toString();
			if (text.equals(day)) {
				System.out.println(day);
				c.click();
				break;
			}
		}
	}

	public void setMonth(String month) {
		List<WebElement> months = driver.findElements(By.xpath("//select[@id='months']/option"));
		Iterator<WebElement> itr = months.iterator();
		while (itr.hasNext()) {
			WebElement click = itr.next();
			String text = click.getText().trim();
			if (text.equals(month)) {
				click.click();
				break;
			}
		}
	}

	public void setYear(String year) {
		List<WebElement> years = driver.findElements(By.xpath("//select[@id='years']/option"));
		Iterator<WebElement> itr = years.iterator();
		while (itr.hasNext()) {
			WebElement click = itr.next();
			String text = click.getText().trim();
			if (text.equals(year)) {
				click.click();
				break;

			}
		}
	}
	
	public void setYourAddressFirstName(String yourAddressFirstName) {
		log.info("entering yourAddressFirstName"+yourAddressFirstName);
		TestBase.logExtentReport("entering yourAddressFirstName"+yourAddressFirstName);
		this.yourAddressFirstName.sendKeys(yourAddressFirstName);
	}
	
	public void setYourAddressLastName(String yourAddressLastName) {
		log.info("entering yourAddressLastName"+yourAddressLastName);
		TestBase.logExtentReport("entering yourAddressLastName"+yourAddressLastName);
		this.yourAddressLastName.sendKeys(yourAddressLastName);
	}
	
	public void setYourAddressCompany(String yourAddressCompany) {
		log.info("entering yourAddressCompany"+yourAddressCompany);
		TestBase.logExtentReport("entering yourAddressCompany"+yourAddressCompany);
		this.yourAddressCompany.sendKeys(yourAddressCompany);
	}
	
	public void setAddress(String address) {
		log.info("entering address"+address);
		TestBase.logExtentReport("entering address"+address);
		this.address.sendKeys(address);
	}
	
	public void setAddress2(String address2) {
		log.info("entering address2"+address2);
		TestBase.logExtentReport("entering address2"+address2);
		this.address2.sendKeys(address2);
	}
	
	public void setYourAddressCity(String yourAddressCity) {
		log.info("entering yourAddressCity"+yourAddressCity);
		TestBase.logExtentReport("entering yourAddressCity"+yourAddressCity);
		this.yourAddressCity.sendKeys(yourAddressCity);
	}
	
	public void setYourAddressState(String yourAddressState) {
		log.info("entering yourAddressState"+yourAddressState);
		TestBase.logExtentReport("entering yourAddressState"+yourAddressState);
		new Select(this.yourAddressState).selectByVisibleText(yourAddressState);
	}
	
	public void setYourAddressPostCode(String yourAddressPostCode) {
		log.info("entering yourAddressPostCode"+yourAddressPostCode);
		TestBase.logExtentReport("entering yourAddressPostCode"+yourAddressPostCode);
		this.yourAddressPostCode.sendKeys(yourAddressPostCode);
	}
	
	public void setAdditionalInformation(String additionalInformation) {
		log.info("entering additionalInformation"+additionalInformation);
		TestBase.logExtentReport("entering additionalInformation"+additionalInformation);
		this.additionalInformation.sendKeys(additionalInformation);
	}

	public void setHomePhone(String homePhone) {
		log.info("entering homePhone"+homePhone);
		TestBase.logExtentReport("entering homePhone"+homePhone);
		this.homePhone.sendKeys(homePhone);
	}
	
	public void setMobilephone(String mobilephone) {
		log.info("entering mobilephone"+mobilephone);
		TestBase.logExtentReport("entering mobilephone"+mobilephone);
		this.mobilephone.sendKeys(mobilephone);
	}
	
	public void setAddressAlias(String addressAlias) {
		log.info("entering addressAlias"+addressAlias);
		TestBase.logExtentReport("entering addressAlias"+addressAlias);
		this.addressAlias.sendKeys(addressAlias);
	}

	public void clickOnRegistration() {
		log.info("clicking on registraion..");
		TestBase.logExtentReport("clicking on registraion..");
		registration.click();
	}
}
