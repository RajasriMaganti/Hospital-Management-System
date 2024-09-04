package com.rajasri.hms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseService {
	public static Connection conn;
	private static Connection createconn() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","rajasri2231");
		System.out.println("Database Connection created Successfully");
		
		return conn;
	}
	
	public static Connection getConnection() throws ClassNotFoundException,SQLException{
		if(conn == null) {
			return createconn();
		}
		return conn;
	}
}
