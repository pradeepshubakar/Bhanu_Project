package com.Sample.CompanyName.ProjectName.pageObjects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.Sample.CompanyName.ProjectName.helper.browserConfiguration.config.ObjectReader;
import com.Sample.CompanyName.ProjectName.helper.javaScript.JavaScriptHelper;
import com.Sample.CompanyName.ProjectName.helper.logger.LoggerHelper;
import com.Sample.CompanyName.ProjectName.helper.select.DropDownHelper;
import com.Sample.CompanyName.ProjectName.helper.wait.WaitHelper;
import com.Sample.CompanyName.ProjectName.testbase.TestBase;

public class ProductCategoryPage {

	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(ProductCategoryPage.class);
	
	WaitHelper waitHelper;
	
	@FindBy(xpath = "//div[@id='layered_block_left']/p")
	public WebElement catalogTxtObj;
	
	@FindBy(xpath = "//div[@id='layer_cart']/div[1]/div[1]/h2")
	public WebElement productAddedSuccessfully;
	
	@FindBy(xpath = "//*[@id=\"center_column\"]/ul/li[1]/div/div[2]/div[2]/a[1]/span")
	public WebElement addToCart;
	
	@FindBy(xpath = "//div[@id='layer_cart']/div[1]/div[2]/div[4]/a/span")
	public WebElement proceedToCheckOut;
	
	@FindBy(xpath = "//[@id='center_column']/ul/li")
	public List<WebElement> tatalProducts;
	
	@FindBy(xpath = " //select[@id='selectProductSort']")
	public WebElement sortBy;
	
	@FindBy(xpath = "//*[@id=\"center_column\"]/ul/li/div/div[2]/div/span[1]")
	public List<WebElement> allPriceElements;
	
	public ProductCategoryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(catalogTxtObj, ObjectReader.reader.getExplicitWait());
		TestBase.logExtentReport("ProductCategoryPage Object created");
		new TestBase().getNavigationScreen(driver);
	}
	
	public void mouseOverOnProduct(int number) {
		String fPart = "//*[@id='center_column']/ul/li[";
		String sPart = "]/div/div[2]/h5/a";
		Actions action = new Actions(driver);
		log.info("doing mouse over on: "+number+"..product");
		TestBase.logExtentReport("doing mouse over on: "+number+"..product");
		action.moveToElement(driver.findElement(By.xpath(fPart+number+sPart))).build().perform();
		
	}
	
	public void clickOnAddToCart() {
		log.info("clicking on add to cart");
		TestBase.logExtentReport("clicking in add to cart");
		addToCart.click();
	}
	
	public void clickOnProceedToCheckOut() {
		log.info("clicking on proceed To CheckOut");
		TestBase.logExtentReport("clicking on proceed To CheckOut");
		proceedToCheckOut.click();
	}
	
	public void selectColor(String data) {
		new JavaScriptHelper(driver).scrollIntoView(driver.findElement(By.xpath("//a[contains(text(),'"+data+"')]/parent::*/preceding-sibling::input[1]")));
		driver.findElement(By.xpath("//a[contains(text(),'"+data+"')]/parent::*/preceding-sibling::input[1]")).click();
		try {
			Thread.sleep(7000);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void selectSmallSize() {
		log.info("Selecting small size...");
		TestBase.logExtentReport("Selecting small size...");
		driver.findElement(By.xpath("//input[@id='layered_id_attribute_group_1']")).click();;
	}
	
	public void selectMediumSize() {
		log.info("Selecting Medium Size..");
		TestBase.logExtentReport("Selecting Medium Size..");
		try {
			boolean selected = driver.findElement(By.xpath("//input[@id='layered_id_attribute_group_2']")).isSelected();
			if (!selected) {
				driver.findElement(By.xpath("//input[@id='layered_id_attribute_group_2']")).click();
				log.info("checkbox is checked..	");
				TestBase.logExtentReport("checkbox is checked..	");
			}
		}
		catch(Exception e) {
			log.info("checkbox was already checked..");
			TestBase.logExtentReport("checkbox was already checked..");
		}
	}
	
	public void selectLSize() {

		log.info("Selecting Large Size..");
		TestBase.logExtentReport("Selecting Large Size..");
		try {
			boolean selected = driver.findElement(By.xpath("//input[@id='layered_id_attribute_group_3']")).isSelected();
			if (!selected) {
				driver.findElement(By.xpath("//input[@id='layered_id_attribute_group_3']")).click();
				log.info("checkbox is checked..	");
				TestBase.logExtentReport("checkbox is checked..	");
			}
		}
		catch(Exception e) {
			log.info("checkbox was already checked..");
			TestBase.logExtentReport("checkbox was already checked..");
		}
	
	}
	
	public void selectFirstProduct() {
		Actions obj = new Actions(driver);
		log.info("performing mouse over on first product of page..");
		TestBase.logExtentReport("performing mouse over on first product of page..");
		obj.moveToElement(tatalProducts.get(0)).build().perform();
		log.info("clicking on add to basket..");
		TestBase.logExtentReport("clicking on add to basket..");
		addToCart.click();
	}
	
	public int getTotalProducts() {
		return tatalProducts.size();
	}
	
	public List<WebElement> getAllProductPrice() {
		return allPriceElements;
	}
	
	public void selectSortByFilter(String dataToSelect) {
		DropDownHelper dropdown = new DropDownHelper(driver);
		dropdown.selectUsingVisibleText(sortBy, dataToSelect);
		//new DropDownHelper(driver).selectUsingVisibleText(sortBy, dataToSelect);
	}
	
	public boolean verifyArrayHasAscendingData(ArrayList<Integer> array) {
		for (int i = 0; i < array.size() - 1; i++) {
			// this condition will check all next price should be smaller than previous one.
			// next price can be greater and equal
			if (array.get(i) <= array.get(i + 1)) {

				log.info("obj.get(i): "+array.get(i));
				log.info("obj.get(i + 1): "+array.get(i + 1));

			}
			else {
				Assert.assertTrue(false, "price filter is not working");
				return false;
			}
		
	}
		return true;
}
	
	public ArrayList<Integer> getPriceMassagedData(Iterator<WebElement> itr){
		ArrayList<Integer> array = new ArrayList<Integer>();
		while (itr.hasNext()) {
			String p = itr.next().getText();
			if (p.contains("$")) {
				String actualData = p.substring(1);
				log.info(actualData);
				double p1 = Double.parseDouble(actualData);
				int productPrice = (int) p1;
				array.add(productPrice);
			}
		}
		return array;

	}
}
