package com.Sample.CompanyName.ProjectName.helper.frame;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.Sample.CompanyName.ProjectName.helper.logger.LoggerHelper;

public class FrameHelper {

	private WebDriver driver;
	private Logger log=LoggerHelper.getLogger(FrameHelper.class);
	
	public FrameHelper(WebDriver driver) {
		this.driver=driver;
	}
	/**
	 * This method will switchToFrame based on Frmae Index
	 * @param FrmaeIndex
	 */
	public void switchToFrame(int FrmaeIndex) {
		driver.switchTo().frame(FrmaeIndex);
		log.info("switched to :"+FrmaeIndex+" frame");
	}
	/**
	 * This method will switchToFrame based on Frmae Name
	 * @param FrmaeName
	 */
	public void switchToFrame(String FrmaeName) {
		driver.switchTo().frame(FrmaeName);
		log.info("switched to :"+FrmaeName+" frame");
	}
	/**
	 * This method will switchToFrame based on WebElement
	 * @param element
	 */
	
	public void switchToFrame(WebElement element) {
		driver.switchTo().frame(element);
		log.info("switched to :"+element.toString());
	}
	
}
