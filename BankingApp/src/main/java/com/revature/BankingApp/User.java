package com.revature.BankingApp;

import java.util.ArrayList;
import java.util.List;

import com.revature.BankingApp.repository.AccountDAO;
import com.revature.BankingApp.repository.AppDAO;

public class User {

	protected String name;
	protected String username;
	protected String password;
	protected String birthday;
	protected int age;
	protected String city;

	protected List<Account> accountList = new ArrayList<Account>();
	protected static ArrayList<Application> appList = new ArrayList<Application>();

	public User() {
	}

	public User(String name, String username, String password, String birthday, int age, String city) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.birthday = birthday;
		this.age = age;
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void getPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String createApplication(String aType, User user) {
		Application newApp = new Application();
		newApp.appType = aType;
		newApp.approved = "n";
		
		AppDAO appdao = new AppDAO();
		return appdao.insertApp(user.username, newApp);
		
		//appList.add(newApp);
	}

	public void displayUserInfo() {
		System.out.println("Name: " + this.name + "\n" + "Username: " + this.username + "\n" + "Age: " + this.age + "\n"
				+ "Birthday: " + this.birthday + "\n" + "City: " + this.city + "\n" + "Number of accounts: "
				+ this.getNumAccounts() + "\n");
	}

	public void setAccountList(String username) {
		AccountDAO adao = new AccountDAO();
		accountList = adao.getAccounts(username);
	}

	public void displayAccInfo() {
		
		for (Account acc : this.accountList) {
			System.out.println("Account ID: " + acc.accountID + "\n" + "Account type: " + acc.accountType + "\n" + "Amount: " + acc.amount + "\n"
					+ "Number of owners: " + acc.numOwner + "\n ");
		//	acc.getOwners();
		}
	}

	public Account getAccount(int accID) {
		for (Account acc : this.accountList) {
			if (acc.accountID == accID) {
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

	public double depositMoney(User usr, Account acc, double amount) {
		acc.amount = acc.amount + amount;

		AccountDAO adao = new AccountDAO();
		adao.updateAccount(acc.amount, acc.accountID);
		
		accountList = adao.getAccounts(usr.username);
		
		return acc.amount;
	}

	public double withdrawMoney(User usr, Account acc, double amount) {
		acc.amount = acc.amount - amount;

		AccountDAO accdao = new AccountDAO();
		accdao.updateAccount(acc.amount, acc.accountID);
		
		accountList = accdao.getAccounts(usr.username);
		
		return acc.amount;
	}

	public String transferMoney(User usr, Account acc1, Account acc2, double amount) {
		acc1.amount = acc1.amount - amount;

		acc2.amount = acc2.amount + amount;
		
		AccountDAO accdao = new AccountDAO();
		accdao.updateAccount(acc1.amount, acc1.accountID);
		accdao.updateAccount(acc2.amount, acc2.accountID);
		
		accountList = accdao.getAccounts(usr.username);

		String message = acc1.accountType + ": " + String.valueOf(acc1.amount) + "\n" + acc2.accountType + ": "
				+ String.valueOf(acc2.amount);

		return message;
	}

}
