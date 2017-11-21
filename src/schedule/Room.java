package schedule;

import java.io.Serializable;

public class Room implements Serializable {
	private String roomId;
	private double roomAvailability;
	private boolean isBookable;
		
		public Room(String roomId, double roomAvailability){
			this.roomId = roomId;
			this.roomAvailability = roomAvailability;
			this.isBookable = true;
		}
		
	public String getRoomId(){
		return roomId;
	}
	public double getAvailability(){
		return roomAvailability;
	}
	public boolean Bookable(){
		return isBookable;
	}
	public void setRoomId(String room){
		this.roomId = room;
	}
	public void setAvailability(double time){
		this.roomAvailability = time;
	}
	public void notBookable(){
		isBookable = false;
	}
	
	public String toString() {
		return roomId;
	}
}
