package main;

import java.awt.Container;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import graphicsComponents.MainFrame;
import schedule.Booking;
import schedule.Room;
import users.User;

public class SpaceSystem implements Serializable {

	private HashMap<String, Room> rooms = new HashMap<>();
	private HashMap<String, ArrayList<Booking>> bookings = new HashMap<>();
	private HashMap<String, User> users = new HashMap<>();
	private HashMap<Integer, LinkedList<Booking>> pendingRequests = new HashMap<>();
	
	private boolean pendingsSent = true;
	private int generatedActivityID;
	
	private MainFrame gui;
	private boolean resourcesLoaded = false;

	private File usersFile = new File("resources/users.txt");
	private File roomsFile = new File("resources/rooms.txt");
	private File bookingsFile = new File("resources/bookings.txt");
	private File pendingsFile = new File("resources/pendingRequests.txt");
	private File loginInfo = new File("resources/loginInfo.txt");
	

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
	
	public HashMap<Integer, LinkedList<Booking>> getPendingRequests() {
		return pendingRequests;
	}

	public void setPendingRequests(HashMap<Integer, LinkedList<Booking>> pendingRequests) {
		this.pendingRequests = pendingRequests;
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
	
	public boolean isPendingsSent() {
		return pendingsSent;
	}

	public void setPendingsSent(boolean pendingsSent) {
		this.pendingsSent = pendingsSent;
	}
	
	public int generateActivityID() {
		Calendar d = Calendar.getInstance();
		if(isPendingsSent()) {
			setGeneratedActivityID(d.hashCode());
			setPendingsSent(false);
			
		}
		return generatedActivityID;
	}
	
	public int getGeneratedActivityID() {
		return generatedActivityID;
	}

	public void setGeneratedActivityID(int generatedActivityID) {
		this.generatedActivityID = generatedActivityID;
	}

	public void addBooking(Booking b) throws IOException {
		if (getUserBookings(b.getUser()) == null) {
			bookings.put(b.getUser().getUserName(), new ArrayList<Booking>());
		}
		getUserBookings(b.getUser()).add(b);
		updateFile(bookingsFile, bookings);
	}

	public void removeBooking(Booking b) throws IOException {
		getUserBookings(b.getUser()).remove(b);
		updateFile(bookingsFile, bookings);
	}
	
	public void addPendingRequest(Booking b) throws IOException {
		if (getActivityPendingRequests(b.getActivityID()) == null) {
			pendingRequests.put(b.getActivityID(), new LinkedList<Booking>());
		}
		getActivityPendingRequests(b.getActivityID()).add(b);
		updateFile(pendingsFile, pendingRequests);
	}
	
	public void removePendingRequest(int activityID) throws IOException {
		pendingRequests.remove(activityID);
		updateFile(pendingsFile, pendingRequests);
	}

	public void addRoom(Room r) throws IOException {
		rooms.put(r.getRoomId(), r);
		updateFile(roomsFile, rooms);
	}

	public void removeRoom(Room r) throws IOException {
		Collection<User> uList = users.values();
		for(User u: uList) {
			Collection<Booking> bList = getUserBookings(u);
			if(bList != null) {
				Iterator<Booking> iterator = bList.iterator();
				while(iterator.hasNext()) {
					Booking b = iterator.next();
					if(b.getRoom().getRoomId() == r.getRoomId()) {
						iterator.remove();
					}
				}
			}
		}
		rooms.remove(r.getRoomId());
		updateFile(roomsFile, rooms);
	}

	public void addUser(User u) throws IOException {
		users.put(u.getUserName(), u);
		updateLoginInfoFile();
		updateFile(usersFile, users);
	}

	public void removeUser(String u) throws IOException {
		users.remove(u);
		bookings.remove(u);
		updateLoginInfoFile();
		updateFile(usersFile, users);
		updateFile(bookingsFile, bookings);
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
	
	public LinkedList<Booking> getActivityPendingRequests(int activityID) {
		return pendingRequests.get(activityID);
	}
	
	public void updateFile(File f, HashMap m) throws IOException {
		FileOutputStream fos = new FileOutputStream(f);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(m);
		
		fos.close();
		oos.close();
	}
	
	public void confirmRequestRank() throws IOException {
		updateFile(pendingsFile, pendingRequests);
	}
	
	public void updateLoginInfoFile() throws IOException {
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(loginInfo)));
		Collection<User> usersList = users.values();
		for(User u: usersList) {
			out.write(u.getUserName() + " " + u.getPassword());
			out.println();
		}
		out.flush();
		out.close();
	}

	public void loadResources() throws ClassNotFoundException, IOException {
		
		FileInputStream fis = null;
		ObjectInputStream ois = null;

		FileOutputStream fos;
		ObjectOutputStream oos;
		
		try {
			/*
			HashMap<String,User> map = new HashMap<>();
			fos = new FileOutputStream(usersFile);
			oos = new ObjectOutputStream(fos);
			User u = new User("Principal", "a", "1", "blah@gmail.com", 1, 0, this);
			map.put(u.getUserName(),u);
			oos.writeObject(map);
			*/

			fis = new FileInputStream(usersFile);
			ois = new ObjectInputStream(fis);
			while (true) {
				users = (HashMap<String,User>) ois.readObject();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			/*
			HashMap<String,Room> map = new HashMap<>();
			fos = new FileOutputStream(roomsFile);
			oos = new ObjectOutputStream(fos);
			Room room = new Room("ARTS1000", 3, 3, new ArrayList<>(Arrays.asList(0,1)));
			map.put(room.getRoomId(),room);
			oos.writeObject(map);
			*/
			
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
		
		try {
			fis = new FileInputStream(pendingsFile);
			ois = new ObjectInputStream(fis);

			while (true) {
				pendingRequests = (HashMap<Integer,LinkedList<Booking>>) ois.readObject();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		fis.close();
		ois.close();
		resourcesLoaded = true;
	}

	
}
