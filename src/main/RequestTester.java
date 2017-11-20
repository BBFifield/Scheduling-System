package main;

import schedule.Booking;
import schedule.Room;
import users.User;

public class RequestTester {
	
	
	public static void main(String[] args) {
		Room r = new Room("GYM", 9);
		User u = new User("Justin", "justin5609", "sdsd", "test@gmail.com", "all", 0, null);
		Booking b = new Booking(u, r, 0, null);
		
		EmailSender e = new EmailSender(b);
		
		e.SendEmail();
		
		
		

	}

}
