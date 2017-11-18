package main;

import java.util.ArrayList;
import java.util.Iterator;

import schedule.Room;
import users.User;
import schedule.Booking;
import graphicsComponents.MainFrame;

public class SpaceSystem {
	
	private ArrayList<Room> rooms = new ArrayList<Room>();
	private ArrayList<Booking> bookings = new ArrayList<Booking>();
	private ArrayList<User> users = new ArrayList<User>();
	private User userLoggedIn;
	private MainFrame gui;
	
	public SpaceSystem() {}
	
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
	
	public User getUserLoggedIn() {
		return userLoggedIn;
	}

	public void setUserLoggedIn(User userLoggedIn) {
		this.userLoggedIn = userLoggedIn;
	}
	
	public void addGui(MainFrame gui) {
		this.gui = gui;
	}

	public void sendEmail() {
		
	}
	
	public void addBooking(Booking b) {
		bookings.add(b);
		
	}
	
	public void removeBooking(Booking b) {
		Iterator <Booking> bookingIterator = bookings.iterator();
		while (bookingIterator.hasNext()) {
			Booking b2 = bookingIterator.next();
			if (b2.getRoom().equals(b.getRoom()) && b2.getTime() == b.getTime()) {
				 bookings.remove(b2);
			}
				
	}
		
		
	}
	
	public void addRoom(Room r) {
		rooms.add(r);
		
	}
	
	public void removeRoom(Room r) {
		Iterator <Room> roomIterator = rooms.iterator();
		while (roomIterator.hasNext()) {
			Room r2 = roomIterator.next();
			if (r2.getRoomId().equals(r.getRoomId())) {
				rooms.remove(r2);
			}
		}
	}
	
	public void addUser(User u) {
		users.add(u);
		
	}
	
	public void removeUser(User u) {
		Iterator <User> userIterator = users.iterator();
		while (userIterator.hasNext()) {
			User u2 = userIterator.next();
			if (u2.getUserName().equals(u.getUserName())) {
				users.remove(u2);
			}
			
			
		}
	
		
	}
	
	public User searchUser(String userName) {
		ArrayList<User> users = getUsers();
		
		for (int i = 0; i < users.size(); i++) {
			if(users.get(i).getName().equals(userName)) {
				return users.get(i);
			}
		}
		return null;
	}
	
	public Room searchRoom(String r) {
		ArrayList<Room> rooms = getRooms();
		
		for (int i = 0; i < rooms.size(); i++) {
			if(rooms.get(i).getRoomId().equals(r)) {
				return rooms.get(i);
			}
		}
		return null;
		
	}
	
	public Booking searchBooking(String b) {
		ArrayList<Booking> bookings = getBookings();
		
		for (int i = 0; i < bookings.size(); i++) {
			if(bookings.get(i).equals(b)) {
				return bookings.get(i);
			}
		}
		
		return null;
		
	}
}
