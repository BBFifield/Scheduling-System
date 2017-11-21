package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import graphicsComponents.MainFrame;
import schedule.Booking;
import schedule.Room;
import users.User;

public class SpaceSystem implements Serializable {

	private HashMap<String, Room> rooms = new HashMap<>();
	private HashMap<String, ArrayList<Booking>> bookings = new HashMap<>();
	private HashMap<String, User> users = new HashMap<>();
	private MainFrame gui;
	private boolean resourcesLoaded = false;

	private File usersFile = new File("resources/users.txt");
	private File roomsFile = new File("resources/rooms.txt");
	private File bookingsFile = new File("resources/bookings.txt");

	

	public SpaceSystem() throws ClassNotFoundException, IOException {
		loadResources();
	}

	public HashMap<String, Room> getRooms() {
		return rooms;
	}

	public void setRooms(HashMap<String, Room> rooms) {
		this.rooms = rooms;
	}

	public HashMap<String, ArrayList<Booking>> getBookings() {
		return bookings;
	}

	public void setBookings(HashMap<String, ArrayList<Booking>> bookings) {
		this.bookings = bookings;
	}

	public HashMap<String, User> getUsers() {
		return users;
	}

	public void setUsers(HashMap<String, User> users) {
		this.users = users;
	}

	public void addGui(MainFrame gui) {
		this.gui = gui;
	}

	public void sendEmail() {

	} 

	public void addBooking(Booking b) throws IOException {
		if (getUserBookings(b.getUser()) == null) {
			bookings.put(b.getUser().getUserName(), new ArrayList<Booking>());
		}
		getUserBookings(b.getUser()).add(b);
		
		FileOutputStream fos = new FileOutputStream(bookingsFile);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(bookings);
		
		fos.close();
		oos.close();

	}

	public void removeBooking(Booking b) {
		getUserBookings(b.getUser()).remove(b);
	}

	public void addRoom(Room r) throws IOException {
		rooms.put(r.getRoomId(), r);
		
		FileOutputStream fos = new FileOutputStream(roomsFile);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(rooms);
		
		fos.close();
		oos.close();
	}

	public void removeRoom(Room r) {
		rooms.remove(r);
	}

	public void addUser(User u) throws IOException {
		users.put(u.getUserName(), u);
		FileOutputStream fos = new FileOutputStream(usersFile);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(users);
		
		fos.close();
		oos.close();
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

	public ArrayList<Booking> getUserBookings(User u) {
		return bookings.get(u.getUserName());
	}

	public void loadResources() throws ClassNotFoundException, IOException {
		
		FileInputStream fis = null;
		ObjectInputStream ois = null;

		FileOutputStream fos;
		ObjectOutputStream oos;
		
		try {
			//fos = new FileOutputStream(usersFile);
			//oos = new ObjectOutputStream(fos);
			//oos.writeObject(new User("Brandon", "n", "1", "blah@gmail.com", "req", 0, this));

			fis = new FileInputStream(usersFile);
			ois = new ObjectInputStream(fis);
			while (true) {
				users = (HashMap<String,User>) ois.readObject();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			//HashMap<String,Room> map = new HashMap<>();
			//fos = new FileOutputStream(roomsFile);
			//oos = new ObjectOutputStream(fos);
			//Room room = new Room("ARTS1000", 3.5);
			//map.put(room.getRoomId(),room);
			//oos.writeObject(map);
			
			fis = new FileInputStream(roomsFile);
			ois = new ObjectInputStream(fis);

			while (true) {
				rooms = (HashMap<String,Room>) ois.readObject();
			}
		} catch (IOException e) {
			System.out.println("Room objects loaded");
		}

		try {
			fis = new FileInputStream(bookingsFile);
			ois = new ObjectInputStream(fis);

			while (true) {
				bookings = (HashMap<String,ArrayList<Booking>>) ois.readObject();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		fis.close();
		ois.close();
		resourcesLoaded = true;
	}
}
