package users;

import main.SpaceSystem;
import schedule.Booking;
import schedule.Room;
import users.User;

public class Principal extends User {
	
	public Principal(String name, String username, String emailAddress, String permission, int requestCountWeek, SpaceSystem system){
		super(name, username, emailAddress, permission, requestCountWeek, system);
	}
	
	public void addUser(User user){
		super.getSystem().addUser(user);
	}
	public void addRoom (Room room){
		super.getSystem().addRoom(room);
	}
	public void Approvebooking(Booking booking){
		super.getSystem().addBooking(booking);
	} 
}
