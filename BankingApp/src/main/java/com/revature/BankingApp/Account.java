package com.revature.BankingApp;

import java.util.ArrayList;
import java.util.List;

public class Account {
	protected int accountID;
	protected String accountType;
	protected double amount;
	protected boolean approved;

	protected List<String> owner = new ArrayList<>();
	protected int numOwner;

	public Account() {
	}

//	public void setOwnerList(int accID) {
//		AccountDAO adao = new AccountDAO();
//	    owner = adao.getOwners(accID);
//	}

	public Account(int accountID, String accountType, double amount, boolean approved) {
		super();
		this.accountID = accountID;
		this.accountType = accountType;
		this.amount = amount;
		this.approved = approved; 
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

	// return list of names of people who are connected to an accID
//	public void getOwners() {
//		AccountDAO adao = new AccountDAO();
//		
////	    owner = adao.getOwners(accID);
//		for (String own : this.owner) {
//			System.out.println(own + ", ");
//		}
//	}
}
