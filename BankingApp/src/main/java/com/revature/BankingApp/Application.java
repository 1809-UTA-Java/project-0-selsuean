package com.revature.BankingApp;

public class Application {
	protected String appType;
	protected boolean approved;
	
	public Application(String appType, boolean approved) {
		super();
		this.appType = appType;
		this.approved = approved;
	} 
	
	public Application() {}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
	
	
}
