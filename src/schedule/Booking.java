package schedule;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import users.User;


public class Booking implements Serializable {
	private String activityName;
	private User user;
	private Room room;
	private int timeBooked;
	private Calendar dateBooked;
	private String semester;
	private int status;
	private boolean approved = false;
	
	public static final int singleDay = 0;
	public static final int eachWeek = 1;
	public static final int entireSemester = 2;
	
	public Booking(String activityName, User user, Room room, int timeBooked, int status, Calendar dateBooked) {
		this.activityName = activityName;
		this.user = user;
		this.room = room;
		this.timeBooked = timeBooked;
		this.status = status;
		this.dateBooked = dateBooked;
	}
	
	public Booking(String activityName, User user, Room room, int timeBooked, Calendar dateBooked, String semester) {
		this.activityName = activityName;
		this.user = user;
		this.room = room;
		this.timeBooked = timeBooked;
		this.dateBooked = dateBooked;
		this.setSemester(semester);
		this.status = entireSemester;
	}
	
	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
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
	
	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}
	
	public String toString() {
		if(status == singleDay) {
			return "Activity: " + activityName + "     Room: " + room 
				+ "    " + timeBooked + " Hours     " + dateBooked.getTime() + "     Single Day" +
				"     Approved: " + approved;
		}
		else if(status == eachWeek) {
			SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, ''yy, hh:mm: a");
			
			return "Activity: " + activityName + "     Room: " + room 
					+ "     " + timeBooked + " Hours     " + "Start Date: " + sdf.format(dateBooked.getTime()) + "     Weekly" +  
					"     Approved: " + approved;
			}
		else {
			SimpleDateFormat sdf = new SimpleDateFormat("EEE, hh:mm aaa");
			return "Activity: " + activityName + "     Room: " + room 
				+ "    " + timeBooked + " Hours     " + sdf.format(dateBooked.getTime()) + "     Entire " + semester +    
				"     Approved: " + approved;
			
		}
	}

}

