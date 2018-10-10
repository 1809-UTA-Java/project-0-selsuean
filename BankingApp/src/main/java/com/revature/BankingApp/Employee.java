package com.revature.BankingApp;

import java.util.ArrayList;
import java.util.List;

import com.revature.BankingApp.repository.AccountDAO;
import com.revature.BankingApp.repository.UserDAO;

public class Employee extends User {
 //	Employees of the bank should be able to view all of their customers information. This includes:
//	Account information
//	Account balances
//	Personal information
//	Employees should be able to approve/deny open applications for accounts	
	static List<User> userList = new ArrayList<>();
	protected static List<Account> thisAccountList = new ArrayList<>();
		
	public Employee() {
		super();
	}

	public Employee(String name, String username, String password, String birthday, int age, String city, String role) {
		super(name, username, password, birthday, age, city, role);
	}

	public static void viewAccountInfo(String username) {
		AccountDAO adao = new AccountDAO();
		thisAccountList = adao.getAccounts(username);

		for (Account acc : thisAccountList) {
			if (acc.accountType == null) {
				System.out.println("1 pending account. \n");
			}
			else {
			System.out.println("Account ID: " + acc.accountID + "\n" + "Account type: " + acc.accountType + "\n" + "Amount: " + acc.amount + "\n"
					+ "Number of owners: " + acc.numOwner + "\n ");
			}
		}
	}
	
	public static void viewUserInfo() {
		UserDAO udao = new UserDAO();
		userList = udao.getUsers();
		
		for (User usr : userList) {
			System.out.println("Name: " + usr.name + "\n" + "Username: " + usr.username + "\n" + "Age: " + usr.age + "\n"
					+ "Birthday: " + usr.birthday + "\n" + "City: " + usr.city + "\n" + "Number of accounts: "
					+ usr.getNumAccounts() + "\n");
		}
	}
	
	//returns all applications that are not yet approved 
	public static List<Object> approveApplication(String status) {	
		AccountDAO adao = new AccountDAO();
		List<Object> appList = adao.getOpenApplications(status);
//		for (Object obj : appList) {
//			System.out.println(obj + "\n");
//		}
		return appList;
 	
	}

}
