package com.revature.BankingApp;

public class Account {
	protected String accountID;
	protected String accountType;
	protected double amount;
	protected String[] owner;
	protected int numOwner;
	
	public Account() {}
	
	public void getOwners() {
		for (String own : this.owner) {
			System.out.println(own + ", ");
		}
	}
}
