package com.Sample.CompanyName.ProjectName.helper.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.Sample.CompanyName.ProjectName.helper.logger.LoggerHelper;

public class DataBaseHelper {

	private static Logger log = LoggerHelper.getLogger(DataBaseHelper.class);
	
	private static String url = "jdbc:mysql://localhost/person";
	private static String driverName = "com.mysql.jdbc.Driver";
	private static String userName = "root";
	private static String password = "password";
	private static Connection connection; // from java.sql
	//creating the object reference of class, by this we can access private members
	private static DataBaseHelper instance = null;
	
	//this method will create connection internally
	public DataBaseHelper() {
		connection = getSingleInstanceConnection();
	}
	
	//this will create object for class
	public static DataBaseHelper getInstance() {
		if(instance == null) {
			instance = new DataBaseHelper();
		}
		return instance;
	}

	//this method will create the data base connection
	private Connection getSingleInstanceConnection() {
		try {
			// driver has been registered
			Class.forName(driverName);
			try {
				connection = DriverManager.getConnection(url, userName, password);
				if (connection != null) {
					log.info("Connected to data base..");
				}
			} catch (SQLException e) {
				log.error("Failed to create data base connection " + e);
			}
		} catch (ClassNotFoundException e) {
			log.info("Driver not found.. " + e);

		}
		return connection;
	}
	
	//if we need connection we can call this method
	public Connection getConnection() {
		return connection;
	}
	
	public static ResultSet getResultSet(String dbQuery) {
		instance = DataBaseHelper.getInstance();
		connection = instance.getConnection();
		log.info("Executing Query: "+ dbQuery);
		try {
			Statement stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery(dbQuery);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
