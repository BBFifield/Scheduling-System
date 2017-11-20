package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

import schedule.Room;
import users.User;
import schedule.Booking;
import graphicsComponents.MainFrame;

import schedule.Room;
import users.User;
import schedule.Booking;

public class SpaceSystem {
	
	private ArrayList<Room> rooms = new ArrayList<Room>();
	private ArrayList<Booking> bookings = new ArrayList<Booking>();
	private ArrayList<User> users = new ArrayList<User>();
	private MainFrame gui;
	private User userLoggedIn;
	private boolean usersLoaded = false;

	
	File usersFile = new File("resources/users.txt");
	Scanner in = null;
	
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
	
public boolean validate(String userName, String password) {
		
		try {
			in = new Scanner(usersFile);
			String userNameCurrent = "";
			String passwordCurrent = "";
			
			while(in.hasNextLine()) {
				String columnCurrent;
				for(int i = 0; i < 3; i++) {
					columnCurrent = in.next();
					if(i == 1) {
						userNameCurrent = columnCurrent;
					}
					else if(i == 2) {
						passwordCurrent = columnCurrent;
					}
				}
				if(userNameCurrent.equals(userName) && passwordCurrent.equals(password)) {
					if(!usersLoaded) {
						loadUsers();
					}
					return true;
				}
				in.nextLine();
			}
			
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			if(in != null) {
				in.close();
			}
		}
		return false;
	}
	
	public void loadUsers() {
		while(in.hasNextLine()) {
			String name = in.next();
			String username = in.next();
			String password = in.next();
			String email = in.next();
			String permissions = in.next();
			int requestCountWeek = Integer.parseInt(in.next());
			users.add(new User(name,username,password,email,permissions,requestCountWeek,this));
			in.nextLine();
		}
	}
}
