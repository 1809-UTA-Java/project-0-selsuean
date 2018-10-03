package com.revature.BankingApp;

public class Account {
	protected int accountID;
	protected String accountType;
	protected double amount;
	protected String[] owner;
	protected int numOwner;
	
//	public Account(int accountID, String accountType, double amount, String[] owner, int numOwner) {
//		super();
//		this.accountID = accountID;
//		this.accountType = accountType;
//		this.amount = amount;
//		this.owner = owner;
//		this.numOwner = numOwner;
//	}
	
	public Account() {}
	
	public void getOwners() {
		for (String own : this.owner) {
			System.out.println(own + ", ");
		}
	}
}
