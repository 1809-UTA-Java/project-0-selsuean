package com.revature.BankingApp;

public abstract class Person {
	protected String name;
	protected String username;
	// requirements: at least 8 characters
	// at least one number 
	protected String password;
	
//	public Person(String name, String username, String password) {
//		super();
//		this.name = name;
//		this.username = username;
//		this.password = password;
//	}
	
	public Person() {}
	
	public String getName() {
		return this.name;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
}
