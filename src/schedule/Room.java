package schedule;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

public class Room implements Serializable {
	private String roomId;
	private int timeFrom;
	private int timeTo;
	private ArrayList<Calendar> daysAvailable;
	private int semester;
	
	public static final int WINTER_SEMESTER = 0;
	public static final int SUMMER_SEMESTER = 1;
	public static final int FALL_SEMESTER = 2;
	
	public Room(String roomId, int timeFrom, int timeTo, ArrayList<Calendar> daysAvailable, int semester) {
		this.roomId = roomId;
		this.timeFrom = timeFrom;
		this.timeTo = timeTo;
		this.daysAvailable = daysAvailable;
		this.semester = semester;
	}
		
	public String getRoomId(){
		return roomId;
	}
	
	public void setRoomId(String room){
		this.roomId = room;
	}
	
	public int getTimeTo() {
		return timeTo;
	}

	public void setTimeTo(int timeTo) {
		this.timeTo = timeTo;
	}

	public int getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(int timeFrom) {
		this.timeFrom = timeFrom;
	}
	
	public ArrayList<Calendar> getDaysAvailable() {
		return daysAvailable;
	}

	public void setDaysAvailable(ArrayList<Calendar> daysAvailable) {
		this.daysAvailable = daysAvailable;
	}
	
	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public void merge(ArrayList<Calendar> otherDaysAvailable) {
		for(Calendar c1: otherDaysAvailable) {
			boolean availableOnDay = false;
			for(Calendar c2: daysAvailable) {
				if(c1.get(Calendar.DAY_OF_WEEK) == c2.get(Calendar.DAY_OF_WEEK)) {
					availableOnDay = true;
				}
			}
			if(!availableOnDay) {
				daysAvailable.add(c1);
			}
		}
		Comparator c;
		Collections.sort(daysAvailable, new dayOfWeekComparator());
	}
	
	public String toString() {
		ArrayList<String> daysString = new ArrayList<>();
		for(Calendar c: daysAvailable) {
			switch(c.get(Calendar.DAY_OF_WEEK)) {
			case 1:
				daysString.add("M");
				break;
			case 2:
				daysString.add("T");
				break;
			case 3:
				daysString.add("W"); 
				break;
			case 4:
				daysString.add("TH");
				break;
			case 5:
				daysString.add("F");
				break;
			case 6:
				daysString.add("S");
				break;
			case 7:
				daysString.add("SN");
				break;
			}
		}
		return roomId + "    " + timeFrom + ":00 - " + timeTo + ":00" + "    " + daysString;
	}
}
