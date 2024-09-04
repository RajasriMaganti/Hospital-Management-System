package com.rajasri.hms;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class HospitalManagement {
	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		//try with resource so we don't need to close the connection manually
		try(Connection conn = DatabaseService.getConnection()){
			
			Patinet patient = new Patinet(conn);
			Doctor doctor = new Doctor(conn);
			BookAppointment appointment = new BookAppointment(conn, patient, doctor);
			
			while(true) {
				System.out.println("---------HOSPITAL MANAGEMENT SYSTEM---------");
				System.out.println("1. Add Patinets");
				System.out.println("2. Add Doctor");
				System.out.println("3. View Patients");
				System.out.println("4. View Doctors");
				System.out.println("5. Book Appointments");
				System.out.println("6. Exit");
				
				System.out.println("Enter your choice");
				int choice = sc.nextInt();
				
				switch(choice) {
				case 1:
					patient.addPatient();
					//Add patients
					break;
				case 2:
					doctor.addDoctor();
					//Add doctors
					break;
				case 3:
					patient.viewPatients();
					//view patients
					break;
				case 4:
					doctor.viewDoctors();
					//view doctors
					break;
				case 5:
					//Book appointments
					appointment.bookAppointment();
					break;
				case 6:
					sc.close();
					return;
				default :
					System.out.println("Please enter valid choice");
					break;
				}
			}
			
		}
}
}
