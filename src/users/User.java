package users;

import java.io.Serializable;

import main.SpaceSystem;
import schedule.Booking;

import main.SpaceSystem;
import schedule.Booking;

public class User implements Serializable {

	private String name;
	private String userName;
	private String password;
	private String emailAddress;
	private String permissions;
	private int requestCountWeek; 
	private SpaceSystem system;
	
	public User(String name, String userName, String password, String emailAddress, String permissions, int requestCountWeek, SpaceSystem system) {
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.emailAddress = emailAddress;
		this.permissions = permissions;
		this.requestCountWeek = requestCountWeek;
		this.system = system;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public SpaceSystem getSystem() {
		return system;
	}

	public void setSystem(SpaceSystem system) {
		this.system = system;
	}
	
	
	
}