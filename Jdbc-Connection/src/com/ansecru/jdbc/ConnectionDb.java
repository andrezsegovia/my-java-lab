package com.ansecru.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDb {

	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/db_spring_example";
	
	static final String USER = "springuser";
	static final String PASS = "userspring1234";
	
	private static Connection connection;
	
	public Connection initDbConnection(){
		
		if(connection==null){
			
			try {
				
				Class.forName(JDBC_DRIVER);
				connection = DriverManager.getConnection(DB_URL,USER,PASS);
												
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		
		return connection;
	}
}
