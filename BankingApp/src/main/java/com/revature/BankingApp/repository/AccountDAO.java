package com.revature.BankingApp.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.BankingApp.Account;
import com.revature.BankingApp.User;
import com.revature.BankingApp.db.ConnectionUtil;

public class AccountDAO {

	public String insertUser(Account acc) {
		PreparedStatement ps = null;

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO Accounts VALUES (?,?,?)";
			ps = conn.prepareStatement(sql);

			ps.setInt(1, acc.getAccountID());
			ps.setString(2, acc.getAccountType());
			ps.setDouble(3, acc.getAmount());

			ps.executeUpdate();
			;
			ps.close();
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (IOException ex) {
			ex.getMessage();
		}
		return "Account " + acc.getAccountID() + " inserted into system.";
	}

	/**
	 * 
	 * public Account(String accountID, String accountType, double amount)
	 * 
	 * @param usrID
	 * @return a
	 */
	public List<Account> getAccounts(String username) {
		PreparedStatement ps = null;
		Account a = null;
		List<Account> accounts = new ArrayList<>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT Accounts.* FROM Accounts INNER JOIN Junction ON Accounts.accountID = Junction.accountID WHERE Junction.username = ?";
			// SELECT Accounts.*
			// FROM Accounts
			// INNER JOIN Junction
			// ON Accounts.accountID = Junction.accountID
			// WHERE Junction.username = ?
			ps = conn.prepareStatement(sql);

			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				int accountID = rs.getInt("accountID");
				String accountType = rs.getString("accountType");
				double amount = rs.getDouble("amount");

				a = new Account(accountID, accountType, amount);
				accounts.add(a);
			}

			rs.close();
			ps.close();

		} catch (SQLException ex) {
			ex.getMessage();
		} catch (IOException ex) {
			ex.getMessage();
		}

		return accounts;
	}
	
	public List<String> getOwners(int accountID) {
		PreparedStatement ps = null;
	//	User u = null;
		List<String> owners = new ArrayList<>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT Users.name FROM Users INNER JOIN Junction ON Users.username = Junction.username WHERE Junction.accountID = ?";
			
			ps = conn.prepareStatement(sql);

			ps.setInt(1, accountID);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				String name = rs.getString("name");
//				String username = rs.getString("username");
//				String password = rs.getString("password");
//				String birthday = rs.getString("birthday");
//				int age = rs.getInt("age");
//				String city = rs.getString("city");

				//u = new User(name, username, password, birthday, age, city);
				owners.add(name);
			}

			rs.close();
			ps.close();

		} catch (SQLException ex) {
			ex.getMessage();
		} catch (IOException ex) {
			ex.getMessage();
		}

		return owners;
	}

}
