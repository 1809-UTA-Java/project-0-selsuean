package com.revature.BankingApp;

public class Account {
	protected String accountType;
	protected double amount;
	protected String[] owner;
	protected int numOwner;
	
	public Account(String accountType, double amount, String[] owner, int numOwner) {
		super();
		this.accountType = accountType;
		this.amount = amount;
		this.owner = owner;
		this.numOwner = numOwner;
	}
	
	public Account() {}
}
