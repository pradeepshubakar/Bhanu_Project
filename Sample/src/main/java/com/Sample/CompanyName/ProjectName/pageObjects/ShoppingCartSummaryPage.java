package com.Sample.CompanyName.ProjectName.pageObjects;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Sample.CompanyName.ProjectName.helper.assertion.VerificationHelpler;
import com.Sample.CompanyName.ProjectName.helper.browserConfiguration.config.ObjectReader;
import com.Sample.CompanyName.ProjectName.helper.logger.LoggerHelper;
import com.Sample.CompanyName.ProjectName.helper.wait.WaitHelper;
import com.Sample.CompanyName.ProjectName.testbase.TestBase;

public class ShoppingCartSummaryPage {

	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(RegistrationPage.class);

	WaitHelper waitHelper;
	
	@FindBy(xpath = "//*[@id=\"columns\"]/div[1]/span[2]")
	public WebElement yourShoppingCart;
	
	@FindBy(xpath="//a[@title='Delete']")
	public List<WebElement> quantity_delete;
	
	@FindBy(xpath="//p[.='Your shopping cart is empty.']")
	public WebElement emptyShoppingCartMsg;
	
	public ShoppingCartSummaryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(yourShoppingCart, ObjectReader.reader.getExplicitWait());
		log.info("ShoppingCartSummaryPage Object created..");
		TestBase.logExtentReport("ShoppingCartSummaryPage Object created..");
	}

	public boolean verifyProduct(String prod) {
		log.info("selecting product.."+prod);
		TestBase.logExtentReport("selecting product.."+prod);
		WebElement product = driver.findElement(By.xpath("//*[contains(.,'"+prod+"')]"));
		return new VerificationHelpler(driver).isDisplayed(product);
	}
	
	public void deleteProductFromShoppingCart() throws InterruptedException {
		log.info("Deleting all products fro cart..");
		TestBase.logExtentReport("Deleting all products fro cart..");
		List<WebElement> deletes = quantity_delete;
		Iterator<WebElement> itr = deletes.iterator();
		while(itr.hasNext()) {
			itr.next().click();
			Thread.sleep(2000);
		}
		
	}
	
	public boolean verifyEmptyShoppingCartMessage() {
		/*try {
			log.info("Verifying deleted shopping cart message..");
			TestBase.logExtentReport("Verifying deleted shopping cart message..");
			emptyShoppingCartMsg.isDisplayed();
			return true;
		}
		catch(Exception e) {
			return false;
		}*/
		return new VerificationHelpler(driver).isDisplayed(emptyShoppingCartMsg);
	}
}
