package com.mastek.assignment;
import java.util.Scanner;

import com.mastek.assignment.demo.MenuApp;
import com.mastek.assignment.model.Student;

public class MainClass {

	public static void main(String[] args) {
		
		MenuApp menu=new MenuApp();
		try(Scanner sc=new Scanner(System.in)) {
			
			boolean isRunning = true;
			
			while(isRunning) {
				System.out.println("Enter your choice : ");
				System.out.println("1. Insert or Create Record");
				System.out.println("2. Update Record");
				System.out.println("3. Delete Record");
				System.out.println("4. View Record");
				System.out.println("5. Exit");
				int choice=Integer.parseInt(sc.nextLine());
				switch(choice) {
				//insert student record
				case 1:
					System.out.println("Enter Name : ");
					String name = sc.nextLine();
					System.out.println("Enter Email : ");
					String email = sc.nextLine();
					System.out.println("Enter Admission Date : ");
					String admissionDate = sc.nextLine();
					System.out.println("Enter Class : ");
					String studyingClass= sc.nextLine();
					System.out.println("Enter City : ");
					String city = sc.nextLine();
					menu.insertRecord(new Student(name, email, admissionDate, studyingClass, city));
					break;
				case 2:
					System.out.println("List of the students are as follows : \n");
					menu.viewAllRecord();
					menu.updateRecord();
					break;
				case 3:
					System.out.println("List of the students are as follows : \n");
					menu.viewAllRecord();
					System.out.println("\nEnter the name of the student to delete the record :");
					menu.deleteRecord(sc.nextLine());
					break;
				case 4:
					menu.viewRecord();
					break;
				case 5:
					System.out.println("Thank You..");
					isRunning=false;
					break;
				default :
					System.out.println("Not a valid Choice");
					break;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
