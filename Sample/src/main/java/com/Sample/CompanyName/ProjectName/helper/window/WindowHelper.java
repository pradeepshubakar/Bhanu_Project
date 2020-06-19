package com.Sample.CompanyName.ProjectName.helper.window;

import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import com.Sample.CompanyName.ProjectName.helper.logger.LoggerHelper;

public class WindowHelper {

	private  WebDriver driver;
	private  Logger log = LoggerHelper.getLogger(WindowHelper.class);

	public WindowHelper(WebDriver driver) {
		this.driver = driver;
	}
/**
 * This method will switch to parent window
 * @param element
 */
	public void switchToParentWindow() {
		log.info("switching to parent window...");
		driver.switchTo().defaultContent();

	}
/**
 * This method will switch to child window based on index
 * @param index
 */
	public void switchToWindow(int index) {
		Set<String> windows = driver.getWindowHandles();
		int i = 1;
		for (String window : windows) {
			if (i == index) {
				log.info("switch to: "+index+" window");
				driver.switchTo().window(window);
			} else {
				i++;
			}

		}
	}
	/**
	 * This method will close all tabbed window and
	 * switched to main window
	 */
	public void closeAllTabsAndSwitchToMainWindow() {
		/*Set<String> windows = driver.getWindowHandles();
		String mainWindow = driver.getWindowHandle();
		
		for(String window:windows) {
			if (!window.equalsIgnoreCase(mainWindow)) {
				driver.close();
			}
		}
		log.info("switched to main window");
		driver.switchTo().window(mainWindow);*/
		String title = driver.getTitle();
		Set<String> windows = driver.getWindowHandles();
		for(String window:windows) {
			driver.switchTo().window(window);
			if(!driver.getTitle().equalsIgnoreCase(title)) {
				driver.close();
			}
		}
		log.info("All child windows closed and switched to default window");
	}
	/**
	 * This method will do browser back navigation
	 */
	public void navigateBack() {
		log.info("navigating back..");
		driver.navigate().back();
	}
	public void navigateForward() {
		log.info("navigating forward..");
		driver.navigate().forward();
	}
	public void navigateRefresh() {
		log.info("navigating refresh..");
		driver.navigate().refresh();
	}
}

