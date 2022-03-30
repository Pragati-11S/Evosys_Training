package com.mastek.assignment.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mastek.assignment.database.MyConnection;
import com.mastek.assignment.database.QueryUtil;
import com.mastek.assignment.model.Student;

public class MenuApp {
	MyConnection connection1 = new MyConnection();
	Scanner sc=new Scanner(System.in);
	public void insertRecord(Student student) throws SQLException{
		try(Connection connection = connection1.getConnection();
				PreparedStatement pst = connection.prepareStatement(QueryUtil.insertRecordQuery());
				) {
			pst.setString(1, student.getStudentName());
			pst.setString(2, student.getEmail());
			pst.setString(3, student.getAdmissionDate());
			pst.setString(4, student.getStudyingClass());
			pst.setString(5, student.getCity());
			
			int rows = pst.executeUpdate();
			if(rows>0) {
				System.out.println("Record Inserted successfully");
			} else {
				System.out.println("Record Insertion failed..");
			}
		}
	}
	
	public void viewAllRecord() throws SQLException {
		try(Connection connection = connection1.getConnection();
				Statement st=connection.createStatement();
				ResultSet result = st.executeQuery(QueryUtil.viewRecordQuery());) {
			System.out.println("ID | Name | Email | Admission Date | Class | City");
			while(result.next()) {
				printStudent(new Student(result.getInt("StudentID"),result.getString("StudentName"),result.getString("Email"),result.getString("AdmissionDate"),result.getString("StudyingClass"),result.getString("City")));
			}
		}
	}
	
	public void viewRecord() throws SQLException {
		
		boolean isRunning = true;
		
		while(isRunning) {
			System.out.println("Enter your choice to view records : ");
			System.out.println("1. View All Records");
			System.out.println("2. Search & View Record by Student ID ");
			System.out.println("3. Search & View Record by Student Name ");
			System.out.println("4. Exit");
			System.out.println("-----------------------------");
			int choice=Integer.parseInt(sc.nextLine());
			switch(choice) {
			case 1: 
				try(Connection connection = connection1.getConnection();
						Statement st=connection.createStatement();
						ResultSet result = st.executeQuery(QueryUtil.viewRecordQuery());) {
					System.out.println("ID | Name | Email | Admission Date | Class | City");
					while(result.next()) {
						printStudent(new Student(result.getInt("StudentID"),result.getString("StudentName"),result.getString("Email"),result.getString("AdmissionDate"),result.getString("StudyingClass"),result.getString("City")));
					}
				}
				break;
				
			case 2:
				try(Connection connection = connection1.getConnection();
						Statement st=connection.createStatement();
						ResultSet result = st.executeQuery(QueryUtil.viewRecordQueryByID());) {
					System.out.println("ID | Name | Email | Admission Date | Class | City");
					while(result.next()) {
						printStudent(new Student(result.getInt("StudentID"),result.getString("StudentName"),result.getString("Email"),result.getString("AdmissionDate"),result.getString("StudyingClass"),result.getString("City")));
					}
				}
				break;
				
			case 3:
				try(Connection connection = connection1.getConnection();
						Statement st=connection.createStatement();
						ResultSet result = st.executeQuery(QueryUtil.viewRecordQueryByName());) {
					System.out.println("ID | Name | Email | Admission Date | Class | City");
					while(result.next()) {
						printStudent(new Student(result.getInt("StudentID"),result.getString("StudentName"),result.getString("Email"),result.getString("AdmissionDate"),result.getString("StudyingClass"),result.getString("City")));
					}
				}
				break;
			case 4:
				System.out.println("Thank You..");
				isRunning=false;
				break;
			
			default:
				System.out.println("Not a valid choice..");
				break;
			}
		}	
		
	}
	
	private void printStudent(Student student) {
			System.out.println(student.getStudentID()+" | "+student.getStudentName()+" | "+student.getEmail()+" | "+student.getAdmissionDate()+" | "+student.getStudyingClass()+" | "+student.getCity());
	}
	
	public void deleteRecord(String studentName) throws SQLException {
		try(Connection connection = connection1.getConnection();
			Statement st = connection.createStatement();) {
			
			int rows = st.executeUpdate(QueryUtil.deleteRecordQuery(studentName));
			if(rows>0) {
				System.out.println("Record deleted successfully");
			} else {
				System.out.println("Record deletions failed..");
			}
		}
	}
	
	public void updateRecord() throws SQLException {
		
		System.out.println("\nEnter the name of the student to update the record :");
		String studentName=sc.nextLine();
        boolean isRunning = true;
		
		while(isRunning) {
			System.out.println("What do you want to update ??");
			System.out.println("1. Student Name");
			System.out.println("2. Student Email");
			System.out.println("3. Admission Date");
			System.out.println("4. Class");
			System.out.println("5. City");
			System.out.println("6. Exit");
			System.out.println("-----------------------------");
			int choice=Integer.parseInt(sc.nextLine());
			switch(choice) {
			case 1: //update name
				try(Connection connection = connection1.getConnection();
					PreparedStatement pst = connection.prepareStatement(QueryUtil.updateNameQuery(studentName));) {	
					System.out.println("Enter new name : ");
					String newName=sc.nextLine();
					pst.setString(1, newName);
					int rows  = pst.executeUpdate();
					if(rows > 0) {
						System.out.println("Record updated successfully");
					}
				}
				break;
			case 2: //update email
				try(Connection connection = connection1.getConnection();
					PreparedStatement pst = connection.prepareStatement(QueryUtil.updateEmailQuery(studentName));) {	
					System.out.println("Enter new email : ");
					String newEmail=sc.nextLine();
					pst.setString(1, newEmail);
					int rows  = pst.executeUpdate();
					if(rows > 0) {
						System.out.println("Record updated successfully");
					}
				}
				break;
			case 3: //update admission date
				try(Connection connection = connection1.getConnection();
					PreparedStatement pst = connection.prepareStatement(QueryUtil.updateAdmissionDateQuery(studentName));) {	
					System.out.println("Enter new admission date : ");
					String newDate=sc.nextLine();
					pst.setString(1, newDate);
					int rows  = pst.executeUpdate();
					if(rows > 0) {
						System.out.println("Record updated successfully");
					}
				}
				break;
			case 4: //update class
				try(Connection connection = connection1.getConnection();
					PreparedStatement pst = connection.prepareStatement(QueryUtil.updateClassQuery(studentName));) {	
					System.out.println("Enter new class : ");
					String newClass=sc.nextLine();
					pst.setString(1, newClass);
					int rows  = pst.executeUpdate();
					if(rows > 0) {
						System.out.println("Record updated successfully");
					}
				}
				break;
			case 5: //update city
				try(Connection connection = connection1.getConnection();
					PreparedStatement pst = connection.prepareStatement(QueryUtil.updateCityQuery(studentName));) {	
					System.out.println("Enter new city : ");
					String newCity=sc.nextLine();
					pst.setString(1, newCity);
					int rows  = pst.executeUpdate();
					if(rows > 0) {
						System.out.println("Record updated successfully");
					}
				}
				break;
			case 6:
				System.out.println("Thank You..");
				isRunning=false;
				break;
			
			default:
				System.out.println("Not a valid choice..");
				break;
			}
		}
	}
}
