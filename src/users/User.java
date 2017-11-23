package users;

import java.io.IOException;
import java.io.Serializable;

import main.SpaceSystem;
import schedule.Booking;
import schedule.Room;
import main.SpaceSystem;
import schedule.Booking;

public class User implements Serializable {

	private String name;
	private String userName;
	private String password;
	private String emailAddress;
	private int permissions;
	private int requestCountWeek; 
	private SpaceSystem system;
	
	public static final int regularPermissions = 0;
	public static final int adminPermissions = 1;
	
	public User(String name, String userName, String password, String emailAddress, int permissions, int requestCountWeek, SpaceSystem system) {
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
	
	public void addUser(User user) throws IOException{
		if(permissions == adminPermissions) {
			getSystem().addUser(user);
		}
	}
	
	public void addRoom (Room room) throws IOException{
		if(permissions == adminPermissions) {
			getSystem().addRoom(room);
		}
	}
	
	public void approveBooking(Booking booking) throws IOException{
		if(permissions == adminPermissions) {
			getSystem().addBooking(booking);
		}
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

	public int getPermissions() {
		return permissions;
	}

	public void setPermissions(int permissions) {
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