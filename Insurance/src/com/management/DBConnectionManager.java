package com.management;


import java.io.*;
import java.util.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnectionManager {
	
	public static Connection getConnection()
	{
		Connection con=null;
		Properties prop=new Properties();
		try
		{
			FileInputStream fis=new FileInputStream("lib/database.properties");
			prop.load(fis);
			String url=prop.getProperty("DB_URL");
			String user=prop.getProperty("DB_USERNAME");
			String pass=prop.getProperty("DB_PASSWORD");
			
			//load the driver class
			Class.forName(prop.getProperty("DB_DRIVER_CLASS"));
			
			//create the connection
			con=DriverManager.getConnection(url,user,pass);
			
//			System.out.println("Connected");
		
		}
		catch(Exception e)
		{
//			System.out.println("not connected");
			e.printStackTrace();	
		}
		
		return con;
	}
	
		

}


