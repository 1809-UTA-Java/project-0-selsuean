package com.revature.BankingApp.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.revature.BankingApp.Application;
import com.revature.BankingApp.db.ConnectionUtil;

public class AppDAO {

	public String insertApp(String usr, Application app) {
		PreparedStatement ps = null;
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO Applications VALUES (?,?,?,?)";
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, usr);
			ps.setInt(2, app.getAppID());
			ps.setString(3, app.getAppType());
			ps.setString(4, app.getApproved());
			
			ps.executeUpdate();;
			ps.close();
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (IOException ex) {
			ex.getMessage();
		}
		return "Application submitted. Please await approval.";
	}
	
	
}
