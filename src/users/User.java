package users;

import main.System;

public class User {

	private String name;
	private String userName;
	private String emailAddress;
	private String permissions;
	private int requestCountWeek; 
	private System system;
	
	public User(String name, String userName, String emailAddress, String permissions, int requestCountWeek, System system) {
		this.name = name;
		this.userName = userName;
		this.emailAddress = emailAddress;
		this.permissions = permissions;
		this.requestCountWeek = requestCountWeek;
		this.system = system;
	}
	
	public void requestBooking() {
		
	}
	
	public void cancelBooking(Booking b) {
		
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public int getRequestCountWeek() {
		return requestCountWeek;
	}

	public void setRequestCountWeek(int requestCountWeek) {
		this.requestCountWeek = requestCountWeek;
	}

	public System getSystem() {
		return system;
	}

	public void setSystem(System system) {
		this.system = system;
	}
	
	
	
}
