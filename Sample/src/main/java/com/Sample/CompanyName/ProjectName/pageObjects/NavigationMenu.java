package com.Sample.CompanyName.ProjectName.pageObjects;
//this class help us to mouse over actions etc..

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Sample.CompanyName.ProjectName.helper.browserConfiguration.config.ObjectReader;
import com.Sample.CompanyName.ProjectName.helper.logger.LoggerHelper;
import com.Sample.CompanyName.ProjectName.helper.wait.WaitHelper;
import com.Sample.CompanyName.ProjectName.testbase.TestBase;

public class NavigationMenu {

	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(NavigationMenu.class);
	
	WaitHelper waitHelper;
	
	@FindBy(xpath = "//div[@id='block_top_menu']/ul/li[1]/a")
	public WebElement womenMenu;
	

	@FindBy(xpath = "//div[@id='block_top_menu']/ul/li[2]/a")
	public WebElement dressesMenu;
	

	@FindBy(xpath = "//div[@id='block_top_menu']/ul/li[3]/a")
	public WebElement tshirtsMenu;
	
	public NavigationMenu(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper =new WaitHelper(driver);
		waitHelper.waitForElement(womenMenu, ObjectReader.reader.getExplicitWait());
		TestBase.logExtentReport("NavigationMenu Object is created..");
		new TestBase().getNavigationScreen(driver);
	}
	
	public void mouseOver(String data) {
		log.info("doing mouse over on "+data);
		TestBase.logExtentReport("doing mouse over on "+data);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//*[contains(.,'"+data+"')]"))).build().perform();
	}
	//once mouse over done,we have to click on any item it will navigate to product category page
	public ProductCategoryPage clickOnItem(String data) {
		log.info("Clicking on "+data);
		TestBase.logExtentReport("Clicking on "+data);
		driver.findElement(By.xpath("//*[contains(.,'"+data+"')]")).click();
		return new ProductCategoryPage(driver);
	}
	
	public ProductCategoryPage clickOnMenu(WebElement element) {
		log.info("clicking on: "+element.getText());
		TestBase.logExtentReport("clicking on: "+element.getText());
		element.click();
		return new ProductCategoryPage(driver);
	}
}
