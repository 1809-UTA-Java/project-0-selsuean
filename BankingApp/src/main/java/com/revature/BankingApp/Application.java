package com.revature.BankingApp;

public class Application {
	protected String appType;
	protected String approved;
	protected int appID;
	
	public Application(String appType, String approved, int appID) {
		super();
		this.appType = appType;
		this.approved = approved;
		this.appID = appID;
	} 

	public Application() {}
	
	

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public int getAppID() {
		return appID;
	}

	public void setAppID(int appID) {
		this.appID = appID;
	}

	public String getApproved() {
		return approved;
	}

	public String appStatus() {
		return approved;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}
	
	
	
}
