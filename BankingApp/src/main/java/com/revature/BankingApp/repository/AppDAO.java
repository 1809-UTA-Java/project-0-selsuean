package com.revature.BankingApp.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.BankingApp.Application;
import com.revature.BankingApp.User;
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
	
	
	public List<Application> getApplications(String colName, String ans) {
		PreparedStatement ps = null;
		Application app = null;
		List<Application> applications = new ArrayList<>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM Applications WHERE ? = ?";

			ps = conn.prepareStatement(sql);

			ps.setString(1, colName);
			ps.setString(2,  ans);
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				String username = rs.getString("username");
				String appType = rs.getString("appType");
				String approved = rs.getString("approved");
				int appID = rs.getInt("appID");
								
				//String appType, String approved, int appID
				app = new Application(username, appType, approved, appID);
				applications.add(app);
			}

			rs.close();
			ps.close();

		} catch (SQLException ex) {
			ex.getMessage();
		} catch (IOException ex) {
			ex.getMessage();
		}

		return applications;
	}
	
	
	public String updateApp(String approved, int appID) {
		PreparedStatement ps = null;
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE Applications SET approved = ? WHERE appID = ?";
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, approved);
			ps.setInt(2, appID);
			
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (IOException ex) {
			ex.getMessage();
		}
		
		return "";
	}
	
	public Application returnApp(int appid) {
		PreparedStatement ps = null;
		Application app = null; 
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM Applications WHERE AppID = ?";
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, appid);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {

				String username = rs.getString("username");
				String appType = rs.getString("appType");
				String approved = rs.getString("approved");
				int appID = rs.getInt("appID");
				
				app = new Application(username, appType, approved, appID);
			}
			
			rs.close();
			ps.close();			
			
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (IOException ex) {
			ex.getMessage();
		}
		
		return app;
	}

	
}
