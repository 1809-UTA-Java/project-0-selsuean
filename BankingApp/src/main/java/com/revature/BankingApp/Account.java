package com.revature.BankingApp;

public class Account {
	protected int accountID;
	protected String accountType;
	protected double amount;
	
	protected String[] owner;
	protected int numOwner;
	
	public Account() {}
	
	
	
	public Account(int accountID, String accountType, double amount) {
		super();
		this.accountID = accountID;
		this.accountType = accountType;
		this.amount = amount;
	}

	


	public int getAccountID() {
		return accountID;
	}



	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}



	public String getAccountType() {
		return accountType;
	}



	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}



	public double getAmount() {
		return amount;
	}



	public void setAmount(double amount) {
		this.amount = amount;
	}



	public void getOwners() {
		for (String own : this.owner) {
			System.out.println(own + ", ");
		}
	}
}
