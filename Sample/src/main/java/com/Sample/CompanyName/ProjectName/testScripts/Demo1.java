package com.Sample.CompanyName.ProjectName.testScripts;

import org.testng.annotations.Test;

import com.Sample.CompanyName.ProjectName.pageObjects.LoginPage;
import com.Sample.CompanyName.ProjectName.testbase.TestBase;

public class Demo1 extends TestBase {
	
public LoginPage l=new LoginPage(driver);
	@Test
	public void testLoginPage() throws InterruptedException {
		driver.get("http://automationpractice.com/index.php");
		//Thread.sleep(10000);
		l.loginToApplication("bspradee", "shantha@123");
	}
}
