package com.Sample.CompanyName.ProjectName.helper.browserConfiguration.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.Sample.CompanyName.ProjectName.helper.browserConfiguration.BrowserType;
import com.Sample.CompanyName.ProjectName.helper.resource.ResourceHelper;

public class PropertyReader implements ConfigReader {
	private static FileInputStream file;
	public static Properties OR;

	public PropertyReader() {

		try {
			String filePath = ResourceHelper.getResourcePath("/src/main/resources/configfile/config.properties");
			file = new FileInputStream(new File(filePath));
			OR = new Properties();
			OR.load(file);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public int getImplicitWait() {
		return Integer.parseInt(OR.getProperty("implicitwait"));
	}

	public int getExplicitWait() {
		return Integer.parseInt(OR.getProperty("explicitwait"));
	}

	public int getPageLoadTime() {
		return Integer.parseInt(OR.getProperty("pageloadtime"));
	}

	public BrowserType getBrowserType() {
		return BrowserType.valueOf(OR.getProperty("browserType"));
	}

	public String getUrl() {
		//from this condition we can supply the data from pom.xml, if the data is not there in pom.xml it will take from property file.........
		if (System.getProperty("url")!=null) {
			return System.getProperty("url");
		}
		//System.out.println(OR.getProperty("applicationUrl"));
		return OR.getProperty("applicationUrl");
	}

	public String getUserName() {
		if (System.getProperty("userName")!=null) {
			return System.getProperty("userName");
		}

		return OR.getProperty("userName");
	}

	public String getPassword() {
		if (System.getProperty("password")!=null) {
			return System.getProperty("password");
		}

		return OR.getProperty("password");
	}

}
