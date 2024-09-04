package com.rajasri.hms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BookAppointment {
	private Connection connection;
	private Patinet patient;
	private Doctor doctor; 
	Scanner sc = new Scanner(System.in);
	
	public BookAppointment(Connection connection , Patinet patinet , Doctor doctor) {
		this.connection = connection ;
		this.patient  = patinet;
		this.doctor = doctor;
}
		
	public void bookAppointment() throws SQLException {
		System.out.println("Enter Patient ID : ");
		int PatientID = sc.nextInt();
		
		System.out.println("Enter Doctor ID : ");
		int DoctorID = sc.nextInt();
		
		
		System.out.println("Enter appointment date (yyyy-mm-dd) : ");
		String AppointmentDate = sc.next();
		
		if(!patient.getPatientById(PatientID)) {
			System.out.println("Please provide valid Patient ID");
			return;
		}
		
		if(!doctor.getDoctorById(DoctorID)) {
			System.out.println("Please provide valid Doctor ID");
			return;
		}
		if(!checkAvailability(connection, DoctorID, AppointmentDate)) {
			System.out.println("Doctor not Available.");
			return;
		}
		
		String query = "insert into appointments(patient_id,doctor_id,Appointment_date) values(?,?,?)";
		try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
			preparedStatement.setInt(1, PatientID);
			preparedStatement.setInt(2, DoctorID);
			preparedStatement.setString(3, AppointmentDate);
			
			if(preparedStatement.executeUpdate()>0) {
				System.out.println("Appointment booked successfully");
			}
			else {
				System.out.println("Appointments not booked successfully");
			}
	}
}
	
	public boolean checkAvailability(Connection connection , int DoctorID , String AppointmentDate) throws SQLException {
		String query  = "select count(1) from appointments where doctor_id = ? and Appointment_date = ?";
		
		try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
			preparedStatement.setInt(1, DoctorID);
			preparedStatement.setString(2, AppointmentDate);
			
			try(ResultSet rs = preparedStatement.executeQuery()){
				if(rs.next()) {
					return rs.getInt(1)==0;
				}
			}
		}
		return false;
	}
}
