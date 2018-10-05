package com.revature.BankingApp;

public class Account {
	protected String accountID;
	protected String accountType;
	protected double amount;
	protected String[] owner;
	protected int numOwner;
	
	public Account() {}
	
	
	
	public Account(String accountID, String accountType, double amount) {
		super();
		this.accountID = accountID;
		this.accountType = accountType;
		this.amount = amount;
	}



	public void getOwners() {
		for (String own : this.owner) {
			System.out.println(own + ", ");
		}
	}
}
