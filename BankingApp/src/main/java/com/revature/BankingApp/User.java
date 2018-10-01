package com.revature.BankingApp;

public class User extends Person {

	protected String birthday;
	
	public User(String name, String username, String password) {
		super(name, username, password);
		// TODO Auto-generated constructor stub
	}

	public User() {} 
	
	//new User instance automatically sets up user with a new checkings account 
	public void newAccount(String username) {
		Account newAcc = new Account(); 
		newAcc.accountType = "Checkings";
		newAcc.amount = 0;
		newAcc.owner[0] = username;
		newAcc.numOwner = 1;
	}
	
	public static void addAccount(String username, String accountType) {
		Account addAcc = new Account();
		addAcc.accountType = accountType;
		addAcc.amount = 0;
		addAcc.owner[0] = username;
		addAcc.numOwner = 1;
	}
	
	public static double depositMoney(Account acc, double amount) {
		acc.amount = acc.amount + amount;
		
		return acc.amount;
	}
	
	public static double withdrawMoney(Account acc, double amount) {
		acc.amount = acc.amount - amount;
		
		return acc.amount;
	}
	
	public static String transferMoney(Account acc1, Account acc2, double amount) {
		acc1.amount = acc1.amount - amount;
		
		acc2.amount = acc2.amount + amount;
		
		String message = acc1.accountType + ": " + String.valueOf(acc1.amount) + "\n" + acc2.accountType + ": " + String.valueOf(acc2.amount);
		
		return message;
	}
	
}
