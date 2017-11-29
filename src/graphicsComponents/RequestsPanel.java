package graphicsComponents;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import main.SpaceSystem;
import main.UserValidator;
import schedule.Booking;
import schedule.Month;
import schedule.Room;
import java.awt.Font;
import javax.swing.JComboBox;

public class RequestsPanel extends JPanel {

	private CommonFrame frame;
	private SpaceSystem system;
	private WideComboBox monthCB;
	private WideComboBox semesterCB;
	private WideComboBox roomCB;
	private JTable table;
	private JSpinner timeFromSpinner;
	private JSpinner timeToSpinner;
	private JTextField activityTextField;
	private WideComboBox bookingsList;

	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton singleDayRB;
	private JRadioButton weekRB;
	private JLabel lblRequestBooking;
	private JLabel lblActivityName;
	private JLabel lblTimeFrom;
	private JLabel lblTimeTo;
	private JButton btnRequest;

	private JButton btnRemoveBooking;
	private JButton selectDayBookingsButton;
	private JButton myBookingsButton;
	
	private Date date = new Date();
	
	public RequestsPanel(SpaceSystem system, CommonFrame frame) {
		this.system = system;
		if(frame.getClass() == AdminFrame.class) {
			this.frame = (AdminFrame) frame;
		}
		else {
			this.frame = (UserFrame) frame;
		}
		this.roomCB = (WideComboBox) frame.returnComponent(0);
		this.semesterCB = (WideComboBox) frame.returnComponent(1);
		this.monthCB = (WideComboBox) frame.returnComponent(2);
		this.table = (JTable) frame.returnComponent(3);
		initialize();
	}
	
	public void initialize() {
		setLayout(null);
		
		timeFromSpinner = new JSpinner(new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY));
		timeFromSpinner.setBounds(119, 115, 49, 20);
		JSpinner.DateEditor de = new JSpinner.DateEditor(timeFromSpinner, "HH");
		timeFromSpinner.setEditor(de);
		this.add(timeFromSpinner);
		
		timeToSpinner = new JSpinner(new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY));
		timeToSpinner.setBounds(203, 115, 49, 20);
		JSpinner.DateEditor ne1 = new JSpinner.DateEditor(timeToSpinner, "HH");
		timeToSpinner.setEditor(ne1);
		this.add(timeToSpinner);
		
		lblRequestBooking = new JLabel("Request Bookings");
		lblRequestBooking.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblRequestBooking.setBounds(13, 11, 155, 28);
		this.add(lblRequestBooking);
		
		lblTimeTo = new JLabel("Time To");
		lblTimeTo.setBounds(203, 90, 61, 14);
		this.add(lblTimeTo);
		
		lblTimeFrom = new JLabel("Time From");
		lblTimeFrom.setBounds(119, 90, 74, 14); 
		this.add(lblTimeFrom);
		
		activityTextField = new JTextField();
		activityTextField.setBounds(13, 115, 86, 20);
		this.add(activityTextField);
		activityTextField.setColumns(10);
		
		lblActivityName = new JLabel("Activity Name");
		lblActivityName.setBounds(13, 90, 96, 14);
		this.add(lblActivityName);
		
		btnRequest = new JButton("Submit Request");
		btnRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					submitButtonPressed();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnRequest.setBounds(283, 114, 129, 23);
		this.add(btnRequest);
		
		btnRemoveBooking = new JButton("Remove Booking");
		btnRemoveBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					removeButtonPressed();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnRemoveBooking.setBounds(412, 261, 190, 23);
		this.add(btnRemoveBooking);
		
		myBookingsButton = new JButton("My Bookings");
		myBookingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myBookings();
			}
		});
		myBookingsButton.setBounds(213, 261, 190, 23);
		this.add(myBookingsButton);
		
		selectDayBookingsButton = new JButton("Bookings on Selected Day");
		selectDayBookingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bookingsOnSelectedDay();
			}
		});
		selectDayBookingsButton.setBounds(13, 261, 190, 23);
		this.add(selectDayBookingsButton);
		
		bookingsList = new WideComboBox();
		bookingsList.setBounds(238, 69, 0, 0);
		this.add(bookingsList);
		
		JLabel lblBookings = new JLabel("Bookings List");
		lblBookings.setBounds(13, 223, 110, 14);
		this.add(lblBookings);
		
		singleDayRB = new JRadioButton("Request for single day");
		buttonGroup.add(singleDayRB);
		singleDayRB.setBounds(13, 46, 183, 23);
		this.add(singleDayRB);
		
		weekRB = new JRadioButton("Request for each week");
		buttonGroup.add(weekRB);
		weekRB.setBounds(203, 46, 165, 23);
		this.add(weekRB);
		
		JButton btnRankUp = new JButton("Higher");
		btnRankUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rankUp();
			}
		});
		btnRankUp.setBounds(154, 169, 96, 23);
		this.add(btnRankUp);
		
		JButton btnRankDown = new JButton("Lower");
		btnRankDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rankDown();
			}
		});
		btnRankDown.setBounds(260, 169, 96, 23);
		this.add(btnRankDown);
		
		JLabel lblRankPendingRequests = new JLabel("Rank Pending Requests");
		lblRankPendingRequests.setBounds(10, 173, 140, 14);
		this.add(lblRankPendingRequests);
		
		JButton btnConfirmRank = new JButton("Confirm Rank");
		btnConfirmRank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					confirmRank();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnConfirmRank.setBounds(373, 169, 129, 23);
		this.add(btnConfirmRank);
		
		bookingsList = new WideComboBox();
		bookingsList.setBounds(119, 220, 425, 20);
		add(bookingsList);
	
	}

	public void submitButtonPressed() throws IOException {
		String activityName = activityTextField.getText();
		if(!activityName.isEmpty()) {
			if(table.getSelectedRow() != -1 && table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()) != null) {
				int status = 0;
				if(singleDayRB.isSelected()) {
					status = Booking.singleDay;
				}
				else if(weekRB.isSelected()) {
					status = Booking.eachWeek;
				}
				else if(table.getSelectedRow() == 0) {
					status = Booking.entireSemester;
				}
				else {
					JOptionPane.showMessageDialog(this, "Select a day of the week to book a day for the rest of the semester");
					return;
				}
				
				if(roomCB.getItemCount() == 0) { 
					return; 
				}
				String roomName = ((Room) roomCB.getSelectedItem()).getRoomId();
				Date spinnerDateFrom = (Date) timeFromSpinner.getValue();
				Calendar calendarInstanceFrom = Calendar.getInstance();
				
				calendarInstanceFrom.setTime(spinnerDateFrom);
				int monthIndex = ((Month) monthCB.getSelectedItem()).getMonthIndex();
				int weekIndex;
				if(table.getSelectedColumn() == 0) {
					weekIndex = table.getSelectedColumn() + 7;
				}
				else {
					weekIndex = table.getSelectedColumn();
				}
				
				calendarInstanceFrom.set(Calendar.DAY_OF_WEEK, weekIndex);
				
				Date spinnerDateTo = (Date) timeToSpinner.getValue();
				Calendar calendarInstanceTo = Calendar.getInstance();
				calendarInstanceTo.setTime(spinnerDateTo);
				
				int timeFrom = calendarInstanceFrom.get(Calendar.HOUR_OF_DAY);
				int timeTo = calendarInstanceTo.get(Calendar.HOUR_OF_DAY);
				Room r = system.searchRoom(roomName); 
				
				int duration = 0;
				boolean weekDayVerified = false;
				if(timeFrom < timeTo && timeFrom >= r.getTimeFrom() && timeTo <= r.getTimeTo()) {
					for(Calendar c: r.getDaysAvailable()) {
						if(c.get(Calendar.DAY_OF_WEEK) == weekIndex) {
							duration = timeTo - timeFrom;
							weekDayVerified = true;
						}
					}
				}
				if(weekDayVerified == false){
					JOptionPane.showMessageDialog(this, "Make sure that the start and end times fall inside the schedule for the room, and that start time is less than end time.");
					return;
				}
				if(table.getSelectedRow() == 0) {
					String semester = (String) semesterCB.getSelectedItem();
					system.addPendingRequest(new Booking(system.getGeneratedActivityID(),activityName,system.searchUser(UserValidator.userLoggedIn), system.searchRoom(roomName), duration, calendarInstanceFrom, semester));
				}
				else {
					int day = ((int) table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
					calendarInstanceFrom.set(2017, monthIndex, day);
					system.addPendingRequest(new Booking(system.generateActivityID(), activityName,system.searchUser(UserValidator.userLoggedIn), system.searchRoom(roomName), duration, status, calendarInstanceFrom));
				}
				
				Collection<Booking> pendings = system.getActivityPendingRequests(system.generateActivityID());
				initializeBookings(pendings);
				JOptionPane.showMessageDialog(this, "Booking request sent!");
			} 
			else {
				JOptionPane.showMessageDialog(this, "Select a date on the calendar to make a booking on a single day or weekly, "
						+ "or select a day of the week to book for the entire semester");
			}
		}
		else {
			JOptionPane.showMessageDialog(this, "No activity name specified for booking");
		}
	}

	
	public void rankUp() {
		if(!system.isPendingsSent()) {
			if(bookingsList.getItemCount() == 0) {
				JOptionPane.showMessageDialog(this, "You must input a request first in order to rank");
				return;
			}
			else {
				Booking b = (Booking) bookingsList.getSelectedItem();
				LinkedList<Booking> requests = system.getActivityPendingRequests(b.getActivityID());
				if(requests.contains(b)) {
					int index = requests.indexOf(b);
					if(index == 0) {
						return;
					}
					requests.remove(index); 
					requests.add(index - 1, b);
					initializeBookings(requests);
				}
			}
		}
	}
	
	public void rankDown() {
		if(!system.isPendingsSent()) {
			if(bookingsList.getItemCount() == 0) {
				JOptionPane.showMessageDialog(this, "You must input a request first in order to rank");
				return;
			}
			else {
				Booking b = (Booking) bookingsList.getSelectedItem();
				LinkedList<Booking> requests = system.getActivityPendingRequests(b.getActivityID());
				if(requests.contains(b)) {
					int index = requests.indexOf(b);
					if(index == requests.size() - 1) {
						return;
					}
					requests.remove(index);
					requests.add(index + 1, b);
					initializeBookings(requests);
				}
			}
		}
	}
	
	public void confirmRank() throws IOException {
		if(!system.isPendingsSent()) {
			system.confirmRequestRank();
			bookingsList.removeAllItems();
			JOptionPane.showMessageDialog(this, "Requests are now waiting to be approved");
		}
	}
	
	public void removeButtonPressed() throws IOException {
		if(bookingsList.getItemCount() == 0) {
			JOptionPane.showMessageDialog(this, "You must select a booking first in the list above to remove");
		}
		else {
			bookingsList.removeItem(bookingsList.getSelectedItem());
		}
	}
	
	public void initializeBookings(Collection<Booking> bookings) {
		bookingsList.removeAllItems();
		for(Booking b: bookings) {
			bookingsList.addItem(b);
		}
	}
	
	public void myBookings() {
		bookingsList.removeAllItems();
		ArrayList<Booking> bookings = system.getBookings().get(UserValidator.userLoggedIn);
		if(bookings != null) {
			for(int i = 0; i < bookings.size(); i++) {
				((DefaultListModel<Booking>) bookingsList.getModel()).addElement(bookings.get(i));
			}
		}
		else {
			JOptionPane.showMessageDialog(this, "You have no bookings requested or approved to be shown");
		}
	} 
	
	public void bookingsOnSelectedDay() {
		bookingsList.removeAllItems();
	
		boolean selected = false;
		for(int i = 0; i < table.getColumnCount(); i++) {
			if(table.isColumnSelected(i)) {
				selected = true;
			}
			
		}
			
		if(selected == true) {
			int day = (int) table.getValueAt(table.getSelectedRow(), table.getSelectedColumn());
			Collection<ArrayList<Booking>> bList = (Collection<ArrayList<Booking>>) system.getBookings().values();
			if(bList != null) {
				for(ArrayList<Booking> a: bList) {
					for(Booking b: a) {
						if(day == b.getDate().get(Calendar.DAY_OF_MONTH)) {
							((DefaultListModel<Booking>) bookingsList.getModel()).addElement(b);
						
						}
					}
				}
			}
		}
		else {
			JOptionPane.showMessageDialog(this, "You must select a calendar day to see any bookings for a day");
		}
	}

}
