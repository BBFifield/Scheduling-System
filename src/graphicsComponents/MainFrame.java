package graphicsComponents;
import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.awt.Font;


import main.SpaceSystem;
import main.UserValidator;
import schedule.Booking;
import schedule.Room;

import javax.swing.DefaultListModel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
 
public class MainFrame extends JFrame {
	
	private JTable calendar;
	private JComboBox monthCB;
	private JComboBox semesterCB;
	private JSpinner timeSpinner;
	private JSpinner durationSpinner;
	private JComboBox roomCB;
	private JTextField textField;
	private SpaceSystem system;
	private JLabel userLabel;
	private JList<Booking> bookingsList;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton singleDayRB;
	private JRadioButton weekRB;

	public enum Month{
		JANUARY(0, 31, 0, 1), 
		FEBRUARY(1, 28, 4, 1), 
		MARCH(2, 31, 4, 1), 
		APRIL(3, 30, 6, 1), 
		MAY(4, 31, 1, 2), 
		JUNE(5, 30, 4, 2), 
		JULY(6, 31, 6, 2), 
		AUGUST(7, 31, 2, 2), 
		SEPTEMBER(8, 30, 5, 3), 
		OCTOBER(9, 31, 0, 3), 
		NOVEMBER(10, 30, 3, 3), 
		DECEMBER(11, 31, 5, 3);
		
		private final int monthIndex;
		private final int days;
		private final int indexStartDay;
		private final int semester;
		
		Month(int monthIndex, int days, int indexStartDay, int semester) {
			this.monthIndex = monthIndex;
			this.days = days;
			this.indexStartDay = indexStartDay;
			this.semester = semester;
		}
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		this.setBounds(400, 50, 900, 580);
		getContentPane().setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Space System");
	
		userLabel = new JLabel("Welcome " + null);
		userLabel.setBounds(555, 13, 126, 23);
		getContentPane().add(userLabel);
		
		JLabel lblNewLabel = new JLabel("Bookings Calendar");
		lblNewLabel.setBounds(20, 54, 126, 14);
		getContentPane().add(lblNewLabel);
		
		monthCB = new JComboBox(Month.values());
		monthCB.setBounds(20, 79, 126, 20);
		monthCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				initializeCalendar();
			}
		});
		getContentPane().add(monthCB);
		
		calendar = new JTable(7, 7);
		calendar.setBounds(20, 110, 376, 112);
		initializeCalendar();
		getContentPane().add(calendar);
		
		Date date = new Date();
		timeSpinner = new JSpinner(new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY));
		timeSpinner.setBounds(690, 155, 132, 20);
		JSpinner.DateEditor de = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
		timeSpinner.setEditor(de);
		getContentPane().add(timeSpinner);
		
		durationSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 3, 1));
		durationSpinner.setBounds(690, 186, 132, 20);
		JSpinner.NumberEditor ne1 = new JSpinner.NumberEditor(durationSpinner);
		durationSpinner.setEditor(ne1);
		getContentPane().add(durationSpinner);
		
		JLabel lblNewLabel_1 = new JLabel("Request and Remove Bookings");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 11, 232, 23);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Request Booking");
		lblNewLabel_3.setBounds(625, 69, 119, 14);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Length");
		lblNewLabel_4.setBounds(555, 189, 46, 14);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Time");
		lblNewLabel_5.setBounds(555, 158, 46, 14);
		getContentPane().add(lblNewLabel_5);
		
		textField = new JTextField();
		textField.setBounds(690, 94, 132, 19);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblActivityName = new JLabel("Activity Name");
		lblActivityName.setBounds(555, 97, 97, 14);
		getContentPane().add(lblActivityName);
		
		JLabel lblNewLabel_6 = new JLabel("Room ");
		lblNewLabel_6.setBounds(555, 130, 46, 14);
		getContentPane().add(lblNewLabel_6);
		
		roomCB = new JComboBox();
		roomCB.setBounds(690, 124, 132, 20);
		getContentPane().add(roomCB);
		
		JButton btnNewButton = new JButton("Submit Request");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					submitButtonPressed();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(690, 230, 132, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnRemoveBooking = new JButton("Remove Booking");
		btnRemoveBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					removeButtonPressed();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnRemoveBooking.setBounds(454, 498, 198, 23);
		getContentPane().add(btnRemoveBooking);
		
		JButton myBookingsButton = new JButton("My Bookings");
		myBookingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myBookings();
			}
		});
		myBookingsButton.setBounds(233, 498, 198, 23);
		getContentPane().add(myBookingsButton);
		
		JButton selectDayBookingsButton = new JButton("Bookings on Selected Day");
		selectDayBookingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bookingsOnSelectedDay();
			}
		});
		selectDayBookingsButton.setBounds(10, 498, 198, 23);
		getContentPane().add(selectDayBookingsButton);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				logout();
			}
		});
		btnLogout.setBounds(733, 13, 89, 23);
		getContentPane().add(btnLogout);
		
		DefaultListModel<Booking> model = new DefaultListModel<>();
		bookingsList = new JList<>(model);
		bookingsList.setBounds(155, 295, 667, 182);
		getContentPane().add(bookingsList);
		
		JLabel lblBookings = new JLabel("Bookings List");
		lblBookings.setBounds(356, 270, 112, 14);
		getContentPane().add(lblBookings);
		
		semesterCB = new JComboBox(new String[]{"Fall", "Winter", "Summer"});
		semesterCB.setBounds(193, 79, 119, 20);
		getContentPane().add(semesterCB);
		
		singleDayRB = new JRadioButton("Request for single day");
		buttonGroup.add(singleDayRB);
		singleDayRB.setBounds(20, 230, 158, 23);
		getContentPane().add(singleDayRB);
		
		weekRB = new JRadioButton("Request for each week");
		buttonGroup.add(weekRB);
		weekRB.setBounds(196, 230, 180, 23);
		getContentPane().add(weekRB);
		
		JButton btnRankUp = new JButton("Higher");
		btnRankUp.setBounds(34, 332, 89, 23);
		getContentPane().add(btnRankUp);
		
		JButton btnRankDown = new JButton("Lower");
		btnRankDown.setBounds(34, 366, 89, 23);
		getContentPane().add(btnRankDown);
		
		JLabel lblRankPendingRequests = new JLabel("Rank Pending Requests");
		lblRankPendingRequests.setBounds(10, 306, 136, 14);
		getContentPane().add(lblRankPendingRequests);
		
		JButton btnConfirmRank = new JButton("Confirm Rank");
		btnConfirmRank.setBounds(20, 400, 112, 23);
		getContentPane().add(btnConfirmRank);
		
		JLabel lblSemester = new JLabel("Semester");
		lblSemester.setBounds(193, 54, 97, 14);
		getContentPane().add(lblSemester);
	}
	
	public void addSystem(SpaceSystem system) {
		this.system = system;
		initializeRooms();
	}
	
	public void initializeCalendar() {
		String[] dayOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
		for(int i = 0; i < calendar.getColumnCount(); i++) {
			calendar.setValueAt(dayOfWeek[i], 0, i);
		}
		
		Month selected = (Month) monthCB.getSelectedItem();
		int dayCount = 0;
		for(int i = 1; i < 7; i++) {
			for(int j = 0; j < 7 && dayCount < selected.days; j++) {
				if(j < selected.indexStartDay && i == 1) {
					calendar.setValueAt(null, 1, j);
				}
				else if(i == 1) {
					dayCount++;
					calendar.setValueAt(dayCount ,1, j);
				}
				else {
					dayCount++;
					calendar.setValueAt(dayCount ,i, j);
				}
			}
		}
	}
	
	public void initializeRooms() {
		Collection<Room> rooms = system.getRooms().values();
		for(Room r: rooms) {
			roomCB.addItem(r);
		}
	}
	
	public void setUserLabel(String userLabel) {
		this.userLabel.setText(userLabel);
	}
	
	public void submitButtonPressed() throws IOException {
		String activityName = textField.getText();
		if(!activityName.isEmpty()) {
			if(calendar.getSelectedRow() != -1) {
				int status = 0;
				if(singleDayRB.isSelected()) {
					status = Booking.singleDay;
				}
				else if(weekRB.isSelected()) {
					status = Booking.eachWeek;
				}
				else {
					System.out.println("hello");
					status = Booking.entireSemester;
				}
				
				String roomName = ((Room) roomCB.getSelectedItem()).toString();
				Date spinnerDate = (Date) timeSpinner.getValue();
				Calendar date = Calendar.getInstance();
				
				date.setTime(spinnerDate);
				int monthIndex = ((Month) monthCB.getSelectedItem()).monthIndex;
				int weekIndex = calendar.getSelectedRow();
				date.set(Calendar.DAY_OF_WEEK, weekIndex);
				
				int duration = (Integer) durationSpinner.getValue();
				if(calendar.getSelectedRow() == 0) {
					String semester = (String) semesterCB.getSelectedItem();
					system.addBooking(new Booking(activityName,system.searchUser(UserValidator.userLoggedIn), system.searchRoom(roomName), duration, date, semester));
				}
				else {
					int day = ((int) calendar.getValueAt(calendar.getSelectedRow(), calendar.getSelectedColumn()));
					date.set(Calendar.DAY_OF_MONTH, day);
					date.set(Calendar.MONTH, monthIndex);
					system.addBooking(new Booking(activityName,system.searchUser(UserValidator.userLoggedIn), system.searchRoom(roomName), duration, status, date));
				}
				
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
	
	public void removeButtonPressed() throws IOException {
		if(bookingsList.isSelectionEmpty()) {
			JOptionPane.showMessageDialog(this, "You must select a booking first in the list above to remove");
		}
		else {
			DefaultListModel listModel = (DefaultListModel) bookingsList.getModel();
			system.removeBooking(bookingsList.getSelectedValue());
			listModel.removeElement(bookingsList.getSelectedValue());
		}
	}
	
	public void clearBookingsList() {
		DefaultListModel listModel = (DefaultListModel) bookingsList.getModel();
		listModel.removeAllElements();
	}
	
	public void myBookings() {
		clearBookingsList();
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
		clearBookingsList();
	
		boolean selected = false;
		for(int i = 0; i < calendar.getColumnCount(); i++) {
			if(calendar.isColumnSelected(i)) {
				selected = true;
			}
		}
			
		if(selected == true) {
			int day = (int) calendar.getValueAt(calendar.getSelectedRow(), calendar.getSelectedColumn());
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
	
	public void logout() {
		LoginPage login = new LoginPage();
		login.setVisible(true);
		this.dispose();
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				LoginPage login = new LoginPage();
				login.setVisible(true);
			}
		});
	}
}
