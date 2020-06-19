package com.Sample.CompanyName.ProjectName.helper.javaScript;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.Sample.CompanyName.ProjectName.helper.logger.LoggerHelper;

public class JavaScriptHelper {

	private WebDriver driver;
	private Logger log=LoggerHelper.getLogger(JavaScriptHelper.class);
	
	public JavaScriptHelper(WebDriver driver) {
		this.driver = driver;
		log.info("JavaScriptHelper has been initialised");
	}
	/**
	 * this method will execute the script
	 * @param script
	 * @return
	 */
	public Object executeScript(String script) {
		JavascriptExecutor exe=(JavascriptExecutor) driver;
		return exe.executeScript(script);
	}
	/**
	 * this method will execute the script along with 
	 * multiple arguments(... meaning is multiple)
	 * @param script
	 * @param args
	 * @return
	 */
	public Object executeScript(String script,Object...args) {
		JavascriptExecutor exe=(JavascriptExecutor) driver;
		return exe.executeScript(script,args);
	}
	/**
	 * this method will scroll  to element 
	 * @param element
	 */
	public void scrollToElement(WebElement element) {
		executeScript("window.scrollTo(arguments[0],arguments[1])", element.getLocation().x,element.getLocation().y);
		
	}
	/**
	 * this method will scroll  to element and click on element
	 * @param element
	 */
	public void scrollToElementAndClick(WebElement element) {
		scrollToElement(element);
		element.click();
		log.info("element is clicked"+element.toString());
	}
	/**
	 * this method will scroll till to web element
	 * @param element
	 */
	public void scrollIntoView(WebElement element) {
		log.info("scroll till web element");
		executeScript("arguments[0].scrollIntoView()", element);
	}
	/**
	 * this method will scroll till to web element and click on it
	 * @param element
	 */
	public void scrollIntoViewAndClick(WebElement element) {
		scrollIntoView(element);
		element.click();
		log.info("element is clicked"+element.toString());
	}
	/**
	 * this method will scroll down vertically
	 */
	public void scrollDownVertically() {
		log.info("scrolling down vertically...");
		executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
	/**
	 *  this method will scroll up vertically
	 */
	public void scrollUpVertically() {
		log.info("scrolling up vertically...");
		executeScript("window.scrollTo(0,-document.body.scrollHeight)");
	}
	/**
	 * This method will scroll till given pixel (e.g=1500)
	 * @param pixel
	 */
	
	public void scrollDownByPixel(int pixel) {
		executeScript("windiw.scrollBy(0,"+pixel+")");
	}
	
	public void scrollUpByPixel(int pixel) {
		executeScript("windiw.scrollBy(0,-"+pixel+")");
	}
	/**
	 * This method zoom screen by 100%
	 */
	public void zoomInBy100Percentage() {
		executeScript("document.body.style.zoom='100%'");
	}
	/**
	 * This method zoom screen by 60%
	 */
	public void zoomInBy60Percentage() {
		executeScript("document.body.style.zoom='60%'");
	}
	
	public void zoomInBy40Percentage() {
		executeScript("document.body.style.zoom='40%'");
	}

	/**
	 * This method will click on element
	 * @param element
	 */
	public void clickElement(WebElement element) {
		executeScript("arguments[0].click()", element);
	}
}
