package dev;

/**
*
* Pesron subclasses: customer, employee, admin 
*
* Customer
*/

public abstract class Person {

	protected String name;
	protected String username;
	// requirements: at least 8 characters
	// at least one number 
	protected String password;
	
	public Person (String name, String user, String password) {
		this.name = name;
		this.username = user;
		this.password = password;
	}
	
	public Person() {}
	
	public void createApplication() {

	}

}
