package com.revature.BankingApp;

import java.util.ArrayList;

public class User extends Person {

	protected String birthday;
	protected int age;
	protected String city;
	protected ArrayList<Account> accountList = new ArrayList<Account>();
	protected static ArrayList<Application> appList = new ArrayList<Application>();
	
	//enum for account type?
	
//	public User(String name, String username, String password) {
//		super(name, username, password);
//		// TODO Auto-generated constructor stub
//	}

	public User() {} 
	
	//new User instance automatically sets up user with a new checkings account 
//	public void newAccount(String username) {
//		Account newAcc = new Account(); 
//		accountList.add(newAcc);
//		newAcc.accountType = "Checkings";
//		newAcc.amount = 0;
//		newAcc.owner[0] = username;
//		newAcc.numOwner = 1;
//	}
	
//	public void addAccount(String username, String accountType) {
//		Account addAcc = new Account();
//		accountList.add(addAcc);
//		addAcc.accountType = accountType;
//		addAcc.amount = 0;
//		addAcc.owner[0] = username;
//		addAcc.numOwner = 1;
//	}
	public void createApplication(String aType) {
		Application newApp = new Application();
		newApp.appType = aType;
		newApp.approved = false;
		appList.add(newApp);
	}
	
	public int getNumAccounts() {
		if (accountList.isEmpty()) {
			return 0;
		}
		return this.accountList.size();
	}
	
	public double depositMoney(Account acc, double amount) {
		acc.amount = acc.amount + amount;
		
		return acc.amount;
	}
	
	public double withdrawMoney(Account acc, double amount) {
		acc.amount = acc.amount - amount;
		
		return acc.amount;
	}
	
	public String transferMoney(Account acc1, Account acc2, double amount) {
		acc1.amount = acc1.amount - amount;
		
		acc2.amount = acc2.amount + amount;
		
		String message = acc1.accountType + ": " + String.valueOf(acc1.amount) + "\n" + acc2.accountType + ": " + String.valueOf(acc2.amount);
		
		return message;
	}
	
}
