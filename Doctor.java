package com.rajasri.hms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor {
	
	private Connection conn;
	Scanner sc = new Scanner(System.in);
	
	public Doctor(Connection conn) {
		this.conn = conn;
	}
	
	public void addDoctor() throws SQLException {
		System.out.println("Enter Doctor name:");
		String name = sc.next();
		
		System.out.println("Enter Department:");
		String department = sc.next();
		
		String query = "insert into doctors(name , department) values(?,?)";
		
		try(PreparedStatement ps = conn.prepareStatement(query)){
			ps.setString(1, name);
			ps.setString(2, department);
			
			if(ps.executeUpdate() > 0) {
				System.out.println("Doctor details added Successfully!!");
			}
			else {
				System.out.println("Failed to add Doctor details Successfully!!");
			}
		}
	}
	
	public void viewDoctors() throws SQLException {
		String query = "select * from doctors";
		try(PreparedStatement ps = conn.prepareStatement(query)){
			try(ResultSet rs = ps.executeQuery()){
				System.out.println("Doctor details:");
				
				while(rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String department = rs.getString("department");
					
					System.out.println("Doctor Id:"+id);
					System.out.println("Doctor Name :"+name);
					System.out.println("Department:"+ department );
				}
			}
		}
	}
	
	public boolean getDoctorById(int id) throws SQLException{
		String query = "select count(1) from doctors where id=?";
		
		try(PreparedStatement ps = conn.prepareStatement(query)){
			ps.setInt(1,id);
			
			try(ResultSet rs = ps.executeQuery()){
				if(rs.next()) {
					return rs.getInt(1)>0;
				}
			}
		}
		return false;
	}
}
