package com.Sample.CompanyName.ProjectName.testbase;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.Sample.CompanyName.ProjectName.helper.browserConfiguration.BrowserType;
import com.Sample.CompanyName.ProjectName.helper.browserConfiguration.ChromeBrowser;
import com.Sample.CompanyName.ProjectName.helper.browserConfiguration.FirefoxBrowser;
import com.Sample.CompanyName.ProjectName.helper.browserConfiguration.IExploreBrowser;
import com.Sample.CompanyName.ProjectName.helper.browserConfiguration.config.ObjectReader;
import com.Sample.CompanyName.ProjectName.helper.browserConfiguration.config.PropertyReader;
import com.Sample.CompanyName.ProjectName.helper.excel.ExcelHelper;
import com.Sample.CompanyName.ProjectName.helper.javaScript.JavaScriptHelper;
import com.Sample.CompanyName.ProjectName.helper.logger.LoggerHelper;
import com.Sample.CompanyName.ProjectName.helper.resource.ResourceHelper;
import com.Sample.CompanyName.ProjectName.helper.wait.WaitHelper;
import com.Sample.CompanyName.ProjectName.utils.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class TestBase {

	public static ExtentReports extent;
	public static ExtentTest test;
	public WebDriver driver;
	private Logger log = LoggerHelper.getLogger(TestBase.class);
	public static File reportDirectery;

	@BeforeSuite
	public void beforeSuite() {
		extent = ExtentManager.getInstance();
		// extent = ExtentManager.createInstance("test-output/FirstTest.html");
	}
	
	@BeforeTest
	public void beforeTest() {
		//both are same
		//ConfigReader reader = new PropertyReader();
		ObjectReader.reader = new PropertyReader();
		reportDirectery = new File(ResourceHelper.getResourcePath("/src/main/resources/screenShots"));
		try {
			setUpDriver(ObjectReader.reader.getBrowserType());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		test = extent.createTest(getClass().getSimpleName());
	}
	
	@BeforeMethod
	public void beforeMethod(Method method) {
		test.log(Status.INFO, method.getName() + "****************** test started*********************");
		log.info("****************"+method.getName()+"started*******************");
	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, result.getThrowable());
			//it will capture the screen shot and added to extent report
			String imagePath = captureScreen(result.getName(), driver);
			System.out.println(imagePath);
			test.addScreenCaptureFromPath(imagePath);
		} 
		else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, result.getName() + " is pass");
			
			 String imagePath = captureScreen(result.getName(), driver);
			test.addScreenCaptureFromPath(imagePath);
		}
		else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, result.getThrowable());
		}
		// This will for store the report ,after store only we can see the report
		extent.flush();
	}
	
	@AfterTest
	public void afterTest() {
		if(driver!=null) {
			driver.quit();
		}
	}

	public WebDriver getBrowserObject(BrowserType btype) throws Exception {

		try {
			switch (btype) {
			case Chrome:
				// get Object of ChromeBrowser class
				ChromeBrowser chrome = ChromeBrowser.class.newInstance();
				ChromeOptions options = chrome.getChromeOptions();
				return chrome.getChromeDriver(options);

			case Firefox:
				FirefoxBrowser firefox = FirefoxBrowser.class.newInstance();
				return firefox.getFirefoxDriver(firefox.getFirefoxOptions());

			case Iexplorer:
				IExploreBrowser ie = IExploreBrowser.class.newInstance();
				InternetExplorerOptions cap = ie.getIExplorerCapabilities();
				return ie.getIExplorerDriver(cap);
				
			default:
				throw new Exception("driver not found: "+btype.name());	
			}

		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
		
	}
	
	public void setUpDriver(BrowserType btype) throws Exception {
		driver = getBrowserObject(btype);
		log.info("Initialize Web driver: "+driver.hashCode());
		WaitHelper wait = new WaitHelper(driver);
		wait.setImplicitWait(ObjectReader.reader.getImplicitWait(), TimeUnit.SECONDS);
		wait.pageLoadTime(ObjectReader.reader.getPageLoadTime(), TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public String captureScreen(String fileName , WebDriver driver) {
		if(driver == null) {
			log.info("driver is null..");
			return null;
		}
		if(fileName == "") {
			fileName = "blank";
		}
		File destFile = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);	
		try {
			destFile = new File(reportDirectery+"/"+fileName+"_"+formater.format(calendar.getTime())+".png");
			FileUtils.copyFile(srcFile, destFile);
			Reporter.log("<a href='"+destFile.getAbsolutePath()+"'><img src='"+destFile.getAbsolutePath()+"'height='100' width=100'/></a>");
		}catch(Exception e) {
			e.printStackTrace();
		}
	
		return destFile.toString();
	}
	
	//this method will help us to take screenshot of all reports, no need to call again and again in test scripts
	public void getNavigationScreen(WebDriver driver)  {
		log.info("capturing ui navigation screen...");
		//this for to get full screen shot we are minimizing the sceen
		new JavaScriptHelper(driver).zoomInBy40Percentage();
		//capture the screenshot and as well as added to email report
		String screen = captureScreen("", driver);
		//again we are changing the screen to 100% for object should be locate ,else exception will come
		new JavaScriptHelper(driver).zoomInBy100Percentage();
		//this will add the screenshot to extent report
		try {
			test.addScreenCaptureFromPath(screen);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//This method will help us to create log in extent reports , we just call this method where ever we need log
	public static void logExtentReport(String s1) {
		test.log(Status.INFO, s1);
	}
	
	public void getApplicationUrl(String url) {
		driver.get(url);
		log.info("navigating to ..."+url);
		logExtentReport("navigating to ..."+url);
		
	}
	
	//wrapper method
	public Object[][] getExcelData(String excelName, String sheetName){
		String excelLocation = ResourceHelper.getResourcePath("/src/main/resources/configfile/")+excelName;
		log.info("excel location "+excelLocation);
		logExtentReport("excel location "+excelLocation);
		ExcelHelper excelHelper = new ExcelHelper();
		Object[][] data = excelHelper.getExcelData(excelLocation, sheetName);
		return data;
		
	}
}
