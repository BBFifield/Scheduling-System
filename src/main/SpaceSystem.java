package main;

import java.util.ArrayList;

import schedule.Room;
import users.User;
import schedule.Booking;
import graphicsComponents.Gui;

public class SpaceSystem {
	
	private ArrayList<Room> rooms = new ArrayList<Room>();
	private ArrayList<Booking> bookings = new ArrayList<Booking>();
	private ArrayList<User> users = new ArrayList<User>();
	private Gui gui;
	
	public SpaceSystem() {
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
	
	public void addGui(Gui gui) {
		this.gui = gui;
	}

	public void sendEmail() {
		
	}
	
	public void addBooking(Booking b) {
		bookings.add(b);
		
	}
	
	public Booking removeBooking(Booking b) {
		return b;
		
	}
	
	public void addRoom(Room r) {
		rooms.add(r);
		
	}
	
	public Room removeRoom(Room r) {
		return r;
		
	}
	
	public void addUser(User u) {
		users.add(u);
		
	}
	
	public User removeUser(User u) {
		return u;
		
	}
	
	public User searchUser(String userName) {
		ArrayList<User> users = getUsers();
		
		for (int i = 0; i < users.size(); i++) {
			if(users(i).getName().equals(userName)) {
				
			}
		}
		return null;
		
	}
	
	public Room searchRoom(String r) {
		return null;
		
	}
	
	public Booking searchBooking() {
		return null;
		
	}
}
