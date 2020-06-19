package com.Sample.CompanyName.ProjectName.helper.resource;

public class ResourceHelper {

	public static String getResourcePath(String path) {
		// project location
		// System.out.println(path);

		String basePath = System.getProperty("user.dir");
		 System.out.println(basePath +"/"+ path);
		 // this '/' is we are appending so we can write without / in starting of path....example "/src/main " not required ,only src/main its ok.
		return basePath +"/"+ path;
	}
	
	 
	 /**
	  * this method is example for how getResourcePath will work
	  * @param args
	  */
	
	  public static void main(String[] args) { 
		  
		  String path =ResourceHelper.getResourcePath("src/main/resources/configfile/log4j.properties"); 
		  System.out.println(path);
	  }
	 
}
