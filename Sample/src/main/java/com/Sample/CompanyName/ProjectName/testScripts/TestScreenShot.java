package com.Sample.CompanyName.ProjectName.testScripts;

import org.testng.annotations.Test;

import com.Sample.CompanyName.ProjectName.testbase.TestBase;

public class TestScreenShot extends TestBase {

	@Test
	public void testScreen() {
		driver.get("https://www.amazon.in/");
		getNavigationScreen(driver);
	}
}
