package com.revature.BankingApp;

import java.util.ArrayList;

public class User extends Person {

	protected int userID;
	protected String birthday;
	protected int age;
	protected String city;
	protected ArrayList<Account> accountList = new ArrayList<Account>();
	protected static ArrayList<Application> appList = new ArrayList<Application>();
	

	public User() {} 
	
	public void createApplication(String aType) {
		Application newApp = new Application();
		newApp.appType = aType;
		newApp.approved = false;
		appList.add(newApp);
	}
	
	public void displayUserInfo() {
		System.out.println("Name: " + this.name + "\n" + "Username: " + this.username + "\n" + "Age: "
				+ this.age + "\n" + "Birthday: " + this.birthday + "\n" + "City: " + this.city + "\n"
				+ "Number of accounts: " + this.getNumAccounts() + "\n");
	}
	
	public void displayAccInfo() {
		for (Account acc : this.accountList) {
			System.out.println("Account type: " + acc.accountType + "\n" + "Amount: " + acc.amount + "\n"
					+ "Number of owners: " + acc.numOwner + "\n ");
			acc.getOwners();
		}
	}
	
	public Account getAccount(String accID) {
		for (Account acc : this.accountList) {
			if (acc.accountID.equals(accID)) {
				return acc;
			}
		}
		
		System.out.println("There are no accounts under your user with this acount ID.");
		return null;
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
