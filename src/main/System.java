package main;

import java.util.ArrayList;
import schedule.Room;
import users.User;

public class System {
	
	private ArrayList<Room> rooms = new ArrayList<Room>();
	private ArrayList<Booking> bookings = new ArrayList<Booking>();
	private ArrayList<User> users = new ArrayList<User>();
	
	public System() {
	}
	
	public ArrayList<Room> getRooms() {
		return rooms;
	}

	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}

	public ArrayList<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(ArrayList<Booking> bookings) {
		this.bookings = bookings;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public void sendEmail() {
		
	}
	
	public void addBooking(Booking b) {
		
	}
	
	public Booking removeBooking(Booking b) {
		
	}
	
	public void addRoom(Room r) {
		
	}
	
	public Room removeRoom(Room r) {
		
	}
	
	public void addUser(User u) {
		
	}
	
	public User removeUser(User u) {
		
	}
	
	public User searchUser() {
		
	}
	
	public Room searchRoom() {
		
	}
	
	public Booking searchBooking() {
		
	}
}
