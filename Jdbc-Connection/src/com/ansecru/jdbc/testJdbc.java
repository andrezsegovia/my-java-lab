package com.ansecru.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class testJdbc {

	public static void main(String[] args) {
		
		
		Statement stmt = null;
		Connection connection = null;
		List<Users> users = new ArrayList<Users>(); 
		Users user; 
			
		try {
			
			connection = new ConnectionDb().initDbConnection();
			stmt = connection.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM users");
			
			while(rs.next()){
				
				user = new Users();
				user.setId(Integer.parseInt(rs.getString(1)));
				user.setUsername(rs.getString(2));
				user.setFirst_name(rs.getString(3));
				user.setLast_name(rs.getString(4));
				user.setPhone_number(rs.getString(5));
				user.setPassword(rs.getString(6));
				user.setEmail(rs.getString(7));
				
				users.add(user);
			}
			
			System.out.print(users);
			
			rs.close();
			stmt.close();
			connection.close();
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			try{
				
				if(stmt!=null){
					stmt.close();
				}
				
				if(connection!=null){
					connection.close();
				}
			
			}catch(SQLException e){
				e.printStackTrace();
			}
			
		}
	
		

	}

}
