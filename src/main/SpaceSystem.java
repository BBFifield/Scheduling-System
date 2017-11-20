package main;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

import schedule.Room;
import users.User;
import schedule.Booking;
import graphicsComponents.MainFrame;

public class SpaceSystem {
	
	private HashMap<String,Room> rooms = new HashMap<>();
	private HashMap<User, ArrayList<Booking>> bookings = new HashMap<>();
	private HashMap<String,User> users = new HashMap<>();
	private MainFrame gui;
	private User userLoggedIn;
	private boolean resourcesLoaded = false;

	File loginInfo = new File("resources/loginInfo.txt");
	Scanner in = null;
	
	File usersFile = new File("resources/users.txt");
	File roomsFile = new File("resources/rooms.txt");
	File bookingsFile = new File("resources/bookings.txt");
	
	FileInputStream fis;
	ObjectInputStream ois;
	
	FileOutputStream fos;
	ObjectOutputStream oos;
	
	public SpaceSystem() {}
	
	public User getUserLoggedIn() {
		return userLoggedIn;
	}

	public void setUserLoggedIn(User userLoggedIn) {
		this.userLoggedIn = userLoggedIn;
	}
	
	public HashMap<String,Room> getRooms() {
		return rooms;
	}

	public void setRooms(HashMap<String,Room> rooms) {
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
		rooms.put(r.getRoomId(), r);
	}
	
	public void removeRoom(Room r) {
		rooms.remove(r);	
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
		return rooms.get(r);
	}
	
	public Booking searchBooking(User u) {
		
	}
	
	public boolean validate(String userName, String password) {
		try {
			in = new Scanner(loginInfo);
			String userNameCurrent = "";
			String passwordCurrent = "";
			
			while(in.hasNextLine()) {
				userNameCurrent = in.next();
				passwordCurrent = in.next();
				if(userNameCurrent.equals(userName) && passwordCurrent.equals(password)) {
					if(!resourcesLoaded) {
						loadResources();
						setUserLoggedIn(users.get(userName)); 
					}
					return true;
				}
			}
			
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		finally {
			if(in != null) {
				in.close();
			}
		}
		return false;
	}
	
	public void loadResources() throws ClassNotFoundException {
		try {
			//fos = new FileOutputStream(usersFile);
			//oos = new ObjectOutputStream(fos);
			//oos.writeObject(new User("Brandon", "n", "1", "blah@gmail.com", "req", 0, null));
			
			fis = new FileInputStream(usersFile); 
			ois = new ObjectInputStream(fis);
			while(true) {
				User u = (User) ois.readObject();
				addUser(u);
			}
		}
		catch(IOException e) {
			System.out.println("User objects loaded");
		}
		
		try {
			//fos = new FileOutputStream(roomsFile);
			//oos = new ObjectOutputStream(fos);
			//oos.writeObject(new Room("EN1052", 3.5));
			
			fis = new FileInputStream(roomsFile); 
			ois = new ObjectInputStream(fis);
			while(true) {
				Room r = (Room) ois.readObject();
				addRoom(r);
			}
		}
		catch(IOException e) {
			System.out.println("Room objects loaded");
		}
		
		try {
			//fos = new FileOutputStream(bookingsFile);
			//oos = new ObjectOutputStream(fos);
			//oos.writeObject(new Booking(userLoggedIn, null, 0, null));
			
			fis = new FileInputStream(bookingsFile); 
			ois = new ObjectInputStream(fis);
			while(true) {
				Booking b = (Booking) ois.readObject();
				addBooking(b);
			}
		}
		catch(IOException e) {
			System.out.println("Booking objects loaded");
		}
		
		resourcesLoaded = true;
	}
}
