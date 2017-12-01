package graphicsComponents.frames;

import java.awt.Component;
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
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import graphicsComponents.panels.RequestsPanel;
import graphicsComponents.utils.WideComboBox;
import main.SpaceSystem;
import main.UserValidator;
import schedule.Booking;
import schedule.Month;
import schedule.Room;

public class UserFrame extends CommonFrame {
	
	JPanel requestsPanel;
	private JTable table;
	private WideComboBox monthCB;
	private WideComboBox semesterCB;
	private JSpinner timeFromSpinner;
	private JSpinner timeToSpinner;
	private WideComboBox roomCB;
	private JTextField activityTextField;
	private SpaceSystem system;
	private JLabel userLabel;
	private JButton btnLogout;
	private JList<Booking> bookingsList;

	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton singleDayRB;
	private JRadioButton weekRB;
	private JLabel lblRequestBooking;
	private JLabel lblActivityName;
	private JLabel lblRoom;
	private JLabel lblTimeFrom;
	private JLabel lblTimeTo;
	private JButton btnRequest;

	private JButton btnRemoveBooking;
	private JButton selectDayBookingsButton;
	private JButton myBookingsButton;
	
	private Date date = new Date();
	
	public static final int ROOM_CB = 0;
	public static final int SEMESTER_CB = 1;
	public static final int MONTH_CB = 2;
	public static final int TABLE = 3;

	public UserFrame(SpaceSystem system) {
		this.system = system;
		initialize();
		initializeRooms();
	}
	
	private void initialize() {
		this.setBounds(400, 50, 867, 618);
		getContentPane().setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Space System");
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(20, 13, 200, 29);
		getContentPane().add(tabbedPane);
		
		RequestsPanel panel = new RequestsPanel(system);
		tabbedPane.addTab("Request Bookings", null, panel, null);
	
		userLabel = new JLabel("Welcome " + null);
		userLabel.setBounds(555, 13, 126, 23);
		getContentPane().add(userLabel);
		
		JLabel lblNewLabel = new JLabel("Month");
		lblNewLabel.setBounds(193, 54, 126, 14);
		getContentPane().add(lblNewLabel);
		
		monthCB = new WideComboBox(Month.values());
		monthCB.setBounds(193, 79, 126, 20);
		monthCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				initializeCalendar();
				changeSemester();
			} 
		});
		getContentPane().add(monthCB);
		
		semesterCB = new WideComboBox(new String[]{"Winter", "Summer", "Fall"});
		semesterCB.setBounds(20, 79, 119, 20);
		semesterCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				initializeSemesterCalendar();
				initializeCalendar();
			} 
		});
		getContentPane().add(semesterCB);
		
		int row = 7;	
		int column = 7;
		table = new JTable(row, column);
		table.setBounds(20, 110, 376, 112);
		table.setCellSelectionEnabled(true);
		initializeCalendar();
		getContentPane().add(table);
		
		timeFromSpinner = new JSpinner(new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY));
		timeFromSpinner.setBounds(658, 79, 46, 20);
		JSpinner.DateEditor de = new JSpinner.DateEditor(timeFromSpinner, "HH");
		timeFromSpinner.setEditor(de);
		getContentPane().add(timeFromSpinner);
		
		timeToSpinner = new JSpinner(new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY));
		timeToSpinner.setBounds(733, 79, 46, 20);
		JSpinner.DateEditor ne1 = new JSpinner.DateEditor(timeToSpinner, "HH");
		timeToSpinner.setEditor(ne1);
		getContentPane().add(timeToSpinner);
		
		lblRequestBooking = new JLabel("Request Booking");
		lblRequestBooking.setBounds(509, 133, 119, 14);
		getContentPane().add(lblRequestBooking);
		
		lblTimeTo = new JLabel("Time To");
		lblTimeTo.setBounds(733, 54, 46, 14);
		getContentPane().add(lblTimeTo);
		
		lblTimeFrom = new JLabel("Time From");
		lblTimeFrom.setBounds(658, 54, 76, 14); 
		getContentPane().add(lblTimeFrom);
		
		activityTextField = new JTextField();
		activityTextField.setBounds(555, 164, 136, 19);
		getContentPane().add(activityTextField);
		activityTextField.setColumns(10);
		
		lblActivityName = new JLabel("Activity Name");
		lblActivityName.setBounds(448, 167, 97, 14);
		getContentPane().add(lblActivityName);
		
		lblRoom = new JLabel("Select Room ");
		lblRoom.setBounds(483, 54, 106, 14);
		getContentPane().add(lblRoom);
		
		roomCB = new WideComboBox();
		roomCB.setBounds(442, 79, 187, 20);
		getContentPane().add(roomCB);
		
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
		btnRequest.setBounds(555, 194, 136, 23);
		getContentPane().add(btnRequest);
		
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
		btnRemoveBooking.setBounds(399, 515, 167, 23);
		getContentPane().add(btnRemoveBooking);
		
		myBookingsButton = new JButton("My Bookings");
		myBookingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myBookings();
			}
		});
		myBookingsButton.setBounds(214, 515, 167, 23);
		getContentPane().add(myBookingsButton);
		
		selectDayBookingsButton = new JButton("Bookings on Selected Day");
		selectDayBookingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bookingsOnSelectedDay();
			}
		});
		selectDayBookingsButton.setBounds(20, 515, 167, 23);
		getContentPane().add(selectDayBookingsButton);
		
		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				logout();
			}
		});
		btnLogout.setBounds(733, 13, 89, 23);
		getContentPane().add(btnLogout);
		
		DefaultListModel<Booking> model = new DefaultListModel<>();
		bookingsList = new JList<>(model);
		bookingsList.setBounds(155, 322, 650, 182);
		getContentPane().add(bookingsList);
		
		JLabel lblBookings = new JLabel("Bookings List");
		lblBookings.setBounds(356, 297, 112, 14);
		getContentPane().add(lblBookings);
		
		singleDayRB = new JRadioButton("Request for single day");
		buttonGroup.add(singleDayRB);
		singleDayRB.setBounds(20, 230, 158, 23);
		getContentPane().add(singleDayRB);
		
		weekRB = new JRadioButton("Request for each week");
		buttonGroup.add(weekRB);
		weekRB.setBounds(196, 230, 180, 23);
		getContentPane().add(weekRB);
		
		JButton btnRankUp = new JButton("Higher");
		btnRankUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rankUp();
			}
		});
		btnRankUp.setBounds(34, 332, 89, 23);
		getContentPane().add(btnRankUp);
		
		JButton btnRankDown = new JButton("Lower");
		btnRankDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rankDown();
			}
		});
		btnRankDown.setBounds(34, 366, 89, 23);
		getContentPane().add(btnRankDown);
		
		JLabel lblRankPendingRequests = new JLabel("Rank Pending Requests");
		lblRankPendingRequests.setBounds(10, 306, 136, 14);
		getContentPane().add(lblRankPendingRequests);
		
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
		btnConfirmRank.setBounds(20, 400, 112, 23);
		getContentPane().add(btnConfirmRank);
		
		JLabel lblSemester = new JLabel("Semester");
		lblSemester.setBounds(20, 54, 97, 14);
		getContentPane().add(lblSemester);
	}
	
	public void setUserLabel(String userLabel) {
		this.userLabel.setText(userLabel);
	}
	
	public Component returnComponent(int componentNum) {
		if(componentNum == ROOM_CB) return roomCB;
		else if(componentNum == SEMESTER_CB) return semesterCB;
		else if(componentNum == MONTH_CB) return monthCB;
		else if(componentNum == TABLE) return table;
		return null;
	}
}
