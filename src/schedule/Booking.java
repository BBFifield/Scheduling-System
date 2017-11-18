package schedule;

import java.util.Calendar;
import java.util.Date;
import users.User;


public class Booking {
	private User user;
	private Room room;
	private int timeBooked;
	private Calendar dateBooked;
	private boolean approved = false;
	
	public Booking(User user, Room room, int timeBooked, Calendar dateBooked) {
		this.user = user;
		this.room = room;
		this.timeBooked = timeBooked;
		this.dateBooked = dateBooked;
	}
	
	public User getUser() {
		return user;
	}
	
	public Room getRoom() {
		return room;
	}
	
	public int getTime() {
		return timeBooked;
	}
	
	public Calendar getDate() {
		return dateBooked;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public void setRoom(Room room) {
		this.room = room;
	}
	
	public void setDate(Calendar dateBooked) {
		this.dateBooked = dateBooked;
	}
	
	public void setTime(int timeBooked) {
		this.timeBooked = timeBooked;
	}
	
	public boolean isApproved() {
		return approved;
	}
	
	public void approveBooking() {
		approved = true;
	}
}

