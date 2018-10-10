package com.revature.BankingApp.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.BankingApp.Account;
import com.revature.BankingApp.Admin;
import com.revature.BankingApp.Employee;
import com.revature.BankingApp.Person;
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
			String sql = "INSERT INTO Users VALUES (?,?,?,?,?,?, ?)";
			ps = conn.prepareStatement(sql);

			ps.setString(1, usr.getUsername());
			ps.setString(2, usr.getPassword());
			ps.setString(3, usr.getName());
			ps.setInt(4, usr.getAge());
			ps.setString(5, usr.getBirthday());
			ps.setString(6, usr.getCity());
			ps.setString(7, "C");

			ps.executeUpdate();
			ps.close();
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (IOException ex) {
			ex.getMessage();
		}
		return usr.getName() + " inserted into system.";
	}

	/**
	 * Method to return a User object that matches the username of the passed in usr
	 * object
	 * 
	 * public User(String name, String username, String password, int userID, String
	 * birthday, int age, String city)
	 * 
	 * @param usr
	 * @return u
	 */
	public Person checkForUser(String identifier) {
		PreparedStatement ps = null;
		User u = null;
		Admin a = null;
		Employee e = null;

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
				String role = rs.getString("role");

				if (role.equals("A")) {
					a = new Admin(name, username, password);
					return a;
				}
				if (role.equals("E")) {
					e = new Employee(name, username, password, birthday, age, city, role);
					return e;
				} else if (role.equals("C")) {
					u = new User(name, username, password, birthday, age, city, role);
					return u;
				}

			}

			rs.close();
			ps.close();

		} catch (SQLException ex) {
			ex.getMessage();
		} catch (IOException ex) {
			ex.getMessage();
		}

		return null;
	}

	public User checkUsername(String identifier) {
		PreparedStatement ps = null;
		User u = null;
		Admin a = null;
		Employee e = null;

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
				String role = rs.getString("role");

				u = new User(name, username, password, birthday, age, city, role);

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
	
	public List<User> getUsers() {
		PreparedStatement ps = null;
		User u = null;
		List<User> users = new ArrayList<>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM Users";
			ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				String name = rs.getString("name");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String birthday = rs.getString("birthday");
				int age = rs.getInt("age");
				String city = rs.getString("city");
				String role = rs.getString("role");
				
				u = new User(name, username, password, birthday, age, city, role);
				users.add(u);
			}

			rs.close();
			ps.close();

		} catch (SQLException ex) {
			ex.getMessage();
		} catch (IOException ex) {
			ex.getMessage();
		}

		return users;
	}

}
