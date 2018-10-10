package com.revature.BankingApp.repository;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.revature.BankingApp.Account;
import com.revature.BankingApp.db.ConnectionUtil;

public class AccountDAO {

	public void insertAccount(int accID, String aType) {
		PreparedStatement ps = null;

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO Accounts VALUES (?,?,0,'false')";
			ps = conn.prepareStatement(sql);

			ps.setInt(1, accID);
			ps.setString(2, aType);

			ps.executeUpdate();
			ps.close();
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (IOException ex) {
			ex.getMessage();
		}
	}

	// TODO: insert into junctions (username, accid)
	
	public void insertJunction(String username, int accID) {
		PreparedStatement ps = null;

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO Junction VALUES (?,?)";
			ps = conn.prepareStatement(sql);

			ps.setString(1, username);
			ps.setInt(2, accID);

			ps.executeUpdate();
			ps.close();
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (IOException ex) {
			ex.getMessage();
		}
	}

	// stored prcoedure
	public int getMax() {
		int maxVal = 0;
		CallableStatement cs = null;

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "{ CALL MAX_VAL(?) }";
			cs = conn.prepareCall(sql);
			cs.registerOutParameter(1, Types.INTEGER);

			cs.execute();
			
			maxVal = cs.getInt(1);

			cs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return maxVal;
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
				boolean approved = rs.getBoolean("approved");

				a = new Account(accountID, accountType, amount, approved);
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

	public String updateAccount(double amt, int acc) {
		PreparedStatement ps = null;

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE Accounts SET amount = ? WHERE accountID = ?";
			ps = conn.prepareStatement(sql);

			ps.setDouble(1, amt);
			ps.setInt(2, acc);

			ps.executeUpdate();
			ps.close();

		} catch (SQLException ex) {
			ex.getMessage();
		} catch (IOException ex) {
			ex.getMessage();
		}

		return "";
	}

	public String updateApplicationStatus(int accountID, String status) {
		PreparedStatement ps = null;

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE Accounts SET approved = ? WHERE accountID = ?";
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, status);
			ps.setInt(2, accountID);

			ps.executeUpdate();
			ps.close();

		} catch (SQLException ex) {
			ex.getMessage();
		} catch (IOException ex) {
			ex.getMessage();
		}

		return "Application " + status;
	}

	// SELECT Users.name, Accounts.approved FROM Users INNER JOIN Junction ON
	// Users.username = Junction.username
	// WHERE Junction.accountID = ?

	public List<Object> getOpenApplications(String apprStatus) {
		PreparedStatement ps = null;
		List<Object> owners = new ArrayList<>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT Users.username, Accounts.accountID FROM USERS INNER JOIN Junction ON Users.username = Junction.username INNER JOIN ACCOUNTS ON Junction.accountID = Accounts.accountID WHERE approved = ?";

			ps = conn.prepareStatement(sql);
			ps.setString(1, apprStatus);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				String username = rs.getString("username");
				int accountID = rs.getInt("accountID");

				// owners.add(username + ", for account number "+ accountID);
				owners.add(username);
				owners.add(accountID);
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
