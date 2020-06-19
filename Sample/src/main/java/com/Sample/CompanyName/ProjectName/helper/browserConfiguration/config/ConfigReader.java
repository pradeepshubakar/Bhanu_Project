package com.Sample.CompanyName.ProjectName.helper.browserConfiguration.config;

import com.Sample.CompanyName.ProjectName.helper.browserConfiguration.BrowserType;

public interface ConfigReader {
//double click on ConfigReader - right click - Open Type Hierarchy we can see the where it implemented and all 
	public int getImplicitWait();
	public int getExplicitWait();
	public int getPageLoadTime();
	public BrowserType getBrowserType();
	public String getUrl();
	public String getUserName();
	public String getPassword();
}
