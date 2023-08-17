package com.tyss.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCPractice {

	public static void main(String[] args) {

		try {
			
			//Load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			//Get the connection
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/contactmanager", "root",
					"root");
			//Create the statement & query
			Statement statement = connection.createStatement();
			String query = "select * from tbl_contact";
			
			//Execute the query
			ResultSet resultSet = statement.executeQuery(query);
			
			while(resultSet.next()) {
				System.err.println(resultSet.getInt("id"));
			}
			//Close the connection
			connection.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
