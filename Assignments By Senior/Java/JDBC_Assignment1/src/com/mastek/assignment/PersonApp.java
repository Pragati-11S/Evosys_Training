//Table Structure
//CREATE TABLE EmpSample(
//    EmpID NUMBER NOT NULL,
//    EmpName VARCHAR2(50) NOT NULL,
//    Email VARCHAR2(50) NOT NULL,
//    MobNum NUMBER NOT NULL,
//    JoiningDate DATE NOT NULL,
//    City VARCHAR2(20) NOT NULL,
//    Salary NUMBER NOT NULL,
//    CONSTRAINT PK_EmpSample PRIMARY KEY(EmpID)
//);

package com.mastek.assignment;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import com.mastek.assignment.MyConnection;

public class PersonApp {
	public static void main(String[] args) {
		try {
			Connection connection=MyConnection.getConnection();
			FileInputStream fin=new FileInputStream("D:\\Evosys\\\\Senior Assignments\\\\Java\\\\JDBC_Assignment1\\\\src\\\\com\\\\mastek\\\\assignment\\\\employee.csv");
			Scanner scanner=new Scanner(fin);
			String sqlQuery="INSERT INTO EmpSample1 VALUES(?,?,?,?,to_date(?,'DD-MM-YYYY'),?,?)";
			PreparedStatement pst=connection.prepareStatement(sqlQuery);

            String[] data = new String[7];
            while(scanner.hasNext()) {
            	data=(scanner.nextLine()).split(",");
				pst.setString(1,data[0]);
				pst.setString(2,data[1]);
				pst.setString(3,data[2]);
				pst.setString(4,data[3]);
				pst.setString(5,data[4]);
				pst.setString(6,data[5]);
				pst.setString(7,data[6]);
				pst.addBatch();
			}
			pst.executeBatch();
			scanner.close();
			fin.close();
			connection.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}

