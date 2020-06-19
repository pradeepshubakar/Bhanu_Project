package com.Sample.CompanyName.ProjectName.helper.assertion;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.Sample.CompanyName.ProjectName.helper.logger.LoggerHelper;
import com.Sample.CompanyName.ProjectName.testbase.TestBase;

public class VerificationHelpler {

	private Logger log = LoggerHelper.getLogger(VerificationHelpler.class);
	private WebDriver driver;

	public VerificationHelpler(WebDriver driver) {
		// global and local variables are same
		this.driver = driver;
	}

	public boolean isDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			log.info("element is displayed.." + element.getText());
			TestBase.logExtentReport("element is displayed.." + element.getText());
			return true;
		} catch (Exception e) {
			log.error("element is displayed..", e.getCause());
			TestBase.logExtentReport("element is displayed.."+e.getCause());
			return false;
		}

	}

	public boolean isNotDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			log.info("element is displayed.." + element.getText());
			TestBase.logExtentReport("element is displayed.." + element.getText());
			return false;
		} catch (Exception e) {
			log.error("element is not displayed..");
			return true;
		}

	}

	public String getText(WebElement element) {
		if (null == element) {
			log.info("WebElement is null..");
			return null;
		}
		boolean status = isDisplayed(element);
		if (status) {
			log.info("element text is.." + element.getText());
			return element.getText();
		} else {
			return null;
		}
	}

	public String getTitle() {
		log.info("page title is: " + driver.getTitle());
		return driver.getTitle();
	}
}
