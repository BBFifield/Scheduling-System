package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import schedule.Room;
import users.User;
import schedule.Booking;
import graphicsComponents.MainFrame;

public class SpaceSystem {
	
	private ArrayList<Room> rooms = new ArrayList<Room>();
	private HashMap<User, ArrayList<Booking>> bookings = new HashMap<User, ArrayList<Booking>>();
	private HashMap<String,User> users = new HashMap<String,User>();
	private User userLoggedIn;
	private boolean usersLoaded = false;
	private MainFrame gui;
	
	File usersFile = new File("resources/users.txt");
	Scanner in = null;
	
	public SpaceSystem() {}
	
	public ArrayList<Room> getRooms() {
		return rooms;
	}

	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}

	public HashMap<User, ArrayList<Booking>> getBookings() {
		return bookings;
	}

	public void setBookings(HashMap<User, ArrayList<Booking>> bookings) {
		this.bookings = bookings;
	}

	public HashMap<String,User> getUsers() {
		return users;
	}

	public void setUsers(HashMap<String,User> users) {
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
		if(bookings.get(b.getUser()) == null) {
			bookings.put(b.getUser(), new ArrayList<Booking>());
		}
		bookings.get(b.getUser()).add(b);
		
	}
	
	public void removeBooking(Booking b) {
		bookings.get(b.getUser()).remove(b);		
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
		users.put(u.getUserName(), u);
	}
	
	public void removeUser(String u) {
		users.remove(u);
	}
	
	public User searchUser(String userName) {
		return users.get(userName);
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
						userLoggedIn = users.get(userName); 
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
	
	public void loadUsers() throws FileNotFoundException {
		in = new Scanner(usersFile);
		while(in.hasNextLine()) {
			String name = in.next();
			String username = in.next();
			String password = in.next();
			String email = in.next();
			String permissions = in.next();
			int requestCountWeek = Integer.parseInt(in.next());
			users.put(username, new User(name,username,password,email,permissions,requestCountWeek,this));
		}
		usersLoaded = true;
	}
}
