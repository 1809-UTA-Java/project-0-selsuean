package com.revature.BankingApp.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.BankingApp.User;
import com.revature.BankingApp.db.ConnectionUtil;

public class UserDAO {
	
	/**
	 * User object passed into method gets inserted into db 
	 * 
	 * Columns: username (PK), password, name, age, birthday, city
	 * 
	 * @param usr
	 * @return usr.getName() + " inserted into system."
	 */
	public String insertUser(User usr) {
		PreparedStatement ps = null;
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO Users VALUES (?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, usr.getUsername());
			ps.setString(2, usr.getPassword());
			ps.setString(3, usr.getName());
			ps.setInt(4, usr.getAge());
			ps.setString(5, usr.getBirthday());
			ps.setString(6, usr.getCity());
			
			ps.executeUpdate();;
			ps.close();
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (IOException ex) {
			ex.getMessage();
		}
		return usr.getName() + " inserted into system.";
	}
	
	
	/**
	 * Method to return a User object that matches the username of the passed in usr object 
	 * 
	 * public User(String name, String username, String password, int userID, 
	 * String birthday, int age, String city)
	 * 
	 * @param usr
	 * @return u
	 */
	public User checkForUser(String identifier) {
		PreparedStatement ps = null;
		User u = null; 
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM USERS WHERE USERNAME = ?";
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, identifier);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				String name = rs.getString("name");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String birthday = rs.getString("birthday");
				int age = rs.getInt("age");
				String city = rs.getString("city");
				
				u = new User(name, username, password, birthday, age, city);
			}
			
			rs.close();
			ps.close();			
			
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (IOException ex) {
			ex.getMessage();
		}
		
		return u;
	}

}
