package com.Sample.CompanyName.ProjectName.testScripts;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.Sample.CompanyName.ProjectName.pageObjects.LoginPage;
import com.Sample.CompanyName.ProjectName.testbase.TestBase;

public class Demo extends TestBase {

	/*static {
		System.setProperty("webdriver.chrome.driver", ResourceHelper.getResourcePath("/src/main/resources/drivers/chromedriver.exe"));
	}*/
	@Test
	public void testWindow() throws Exception {
		
		
		/*WebDriver driver=new ChromeDriver();
		WaitHelper wait=new WaitHelper(driver);
		wait.pageLoadTime(05, TimeUnit.SECONDS);
		TestBase tb = TestBase.class.newInstance();
		 tb.setUpDriver(BrowserType.Chrome);*/
		//tb.getBrowserObject(BrowserType.Chrome);
	
		driver.get("http://automationpractice.com/index.php");
		//WindowHelper w = new WindowHelper(driver);
		//w.closeAllTabsAndSwitchToMainWindow();
		//w.switchToWindow(1);
		//Thread.sleep(10000);
		//driver.close();
		driver.findElement(By.xpath("//a[contains(.,'Sign in')]")).click();
		Thread.sleep(5000);
		LoginPage l = new LoginPage(driver);
		l.enterEmailAddress("bspradee@gmail.com");
		l.enterPassword("shantha@123");
		l.clickOnSubmitButton();
		Thread.sleep(10000);
		
	}
}
