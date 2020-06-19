package com.Sample.CompanyName.ProjectName.helper.wait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Sample.CompanyName.ProjectName.helper.logger.LoggerHelper;

public class WaitHelper {

	private WebDriver driver;
	private Logger log=LoggerHelper.getLogger(WaitHelper.class);
	
	public WaitHelper(WebDriver driver) {
		this.driver=driver;
		log.info("WaitHelper Object created..");
	}
	/**
	 * This is ImplicitWait Method
	 * @param timeout
	 * @param unit
	 */
	public void setImplicitWait(long timeout,TimeUnit unit) {
		log.info("Implicit wait has been set to: "+timeout);
		driver.manage().timeouts().implicitlyWait(timeout, unit);
	}
	/**
	 * This will help us to get WebDriverWait Object
	 * @param timeOutInSeconds
	 * @param pollingInEveryMilliSec
	 * @return
	 */
	private WebDriverWait getWait(int timeOutInSeconds,int pollingInEveryMilliSec) {
		WebDriverWait wait=new WebDriverWait(driver, timeOutInSeconds);
		wait.pollingEvery(Duration.ofMillis(pollingInEveryMilliSec));
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(NoSuchFrameException.class);
		
		return wait;
		
	}
	/**
	 * This method will make sure element is visible
	 * @param element
	 * @param timeOutInSeconds
	 * @param pollingInEveryMilliSec
	 */
	public void WaitForElementVisibleWithPollingTime(WebElement element,int timeOutInSeconds,int pollingInEveryMilliSec ) {
		log.info("waiting for :"+element.toString()+"for :"+timeOutInSeconds+" seconds");
		WebDriverWait wait = getWait(timeOutInSeconds, pollingInEveryMilliSec);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("element is visible now");
		
	}
	/**
	 * This method will make sure element is elementToBeClickable
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void WaitForElementClickable(WebElement element,int timeOutInSeconds ) {
		log.info("waiting for :"+element.toString()+"for :"+timeOutInSeconds+" seconds");
		//like this also we can write
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		log.info("element is Clickable now");
		
	}
	/**
	 * This method will make sure invisibilityOf element
	 * @param element
	 * @param timeOutInSeconds
	 * @return
	 */
	//we can write like this also this is best actually
	public boolean WaitForElementNotPresent(WebElement element,long timeOutInSeconds) {
		log.info("waiting for :"+element.toString()+"for :"+timeOutInSeconds+" seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		boolean status = wait.until(ExpectedConditions.invisibilityOf(element));
		log.info("element is invisibile now");
		return status;
		
	}
	
	/**
	 * This method will wait for frameToBeAvailableAndSwitchToIt
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void WaitForFrameToBeAvailableAndSwitchToIt(WebElement element,int timeOutInSeconds ) {
		log.info("waiting for :"+element.toString()+"for :"+timeOutInSeconds+" seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
		log.info("frame is available and switched");
}
	/**
	 * This method will give us fluentWait Object
	 * @param timeOutInSeconds
	 * @param pollingInEveryMilliSec
	 * @return
	 */
	private FluentWait<WebDriver> getfluentWait(int timeOutInSeconds,int pollingInEveryMilliSec) {
		FluentWait<WebDriver> fwait=new FluentWait<WebDriver>(driver);
		fwait.withTimeout(Duration.ofSeconds(timeOutInSeconds));
		fwait.pollingEvery(Duration.ofSeconds(pollingInEveryMilliSec)).ignoring(NoSuchElementException.class);
		return fwait; 
	}
	/**
	 * This method is example for getfluentWait using
	 * @param element
	 * @param timeOutInSeconds
	 * @param pollingInEveryMilliSec
	 * @return
	 */
	public WebElement waitForElement(WebElement element,int timeOutInSeconds,int pollingInEveryMilliSec) {
		FluentWait<WebDriver> fwait = getfluentWait(timeOutInSeconds,pollingInEveryMilliSec);
		fwait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}
	/**
	 * This method will wait for page load
	 * @param timeout
	 * @param unit
	 */
	public void pageLoadTime(long timeout,TimeUnit unit) {
		log.info("Waiting for page to load for: "+ timeout+" "+ unit);
		driver.manage().timeouts().pageLoadTimeout(timeout, unit);
		log.info("page is loaded");
		
	}
	/**
	 * This method will make sure elementToBeClickable
	 * @param element
	 * @param timeOutInSecods
	 */
	public void waitForElement(WebElement element, int timeOutInSecods) {
		log.info("waiting for: "+element.toString()+" for: "+timeOutInSecods+" seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSecods);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("element is visible now");
	}
}
