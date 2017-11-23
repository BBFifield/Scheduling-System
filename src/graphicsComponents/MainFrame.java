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
import users.User;

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
	private JTextField activityTextField;
	private SpaceSystem system;
	private JLabel userLabel;
	private JButton btnLogout;
	private JList<Booking> bookingsList;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton singleDayRB;
	private JRadioButton weekRB;
	private JTextField nameTextField;
	private JLabel lblRequestBooking;
	private JLabel lblActivityName;
	private JLabel lblRoom;
	private JLabel lblTime;
	private JLabel lblLength;
	private JButton btnRequest;
	private JLabel lblUsername;
	private JTextField usernameTextField;
	private JTextField passwordTextField;
	private JLabel lblPassword;
	private JLabel lblEmail;
	private JTextField emailTextField;
	private JButton btnAddUser;
	private JLabel lblUserList;
	private JTextField roomNameTextField;
	private JButton btnRemoveBooking;
	private JButton selectDayBookingsButton;
	private JButton myBookingsButton;
	private JList usersList;
	private JList roomsList;

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
	public MainFrame(SpaceSystem system) {
		this.system = system;
		initialize();
		if(system.searchUser(UserValidator.userLoggedIn).getPermissions() == User.adminPermissions) {
			initializeAdminGui();
			initializeList(usersList, system.getUsers().values());
			initializeList(roomsList, system.getRooms().values());
		}
		initializeRooms();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		this.setBounds(400, 50, 900, 600);
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
		calendar.setCellSelectionEnabled(true);
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
		
		lblRequestBooking = new JLabel("Request Booking");
		lblRequestBooking.setBounds(625, 69, 119, 14);
		getContentPane().add(lblRequestBooking);
		
		lblLength = new JLabel("Length");
		lblLength.setBounds(555, 189, 46, 14);
		getContentPane().add(lblLength);
		
		lblTime = new JLabel("Time");
		lblTime.setBounds(555, 158, 46, 14);
		getContentPane().add(lblTime);
		
		activityTextField = new JTextField();
		activityTextField.setBounds(690, 94, 132, 19);
		getContentPane().add(activityTextField);
		activityTextField.setColumns(10);
		
		lblActivityName = new JLabel("Activity Name");
		lblActivityName.setBounds(555, 97, 97, 14);
		getContentPane().add(lblActivityName);
		
		lblRoom = new JLabel("Room ");
		lblRoom.setBounds(555, 130, 46, 14);
		getContentPane().add(lblRoom);
		
		roomCB = new JComboBox();
		roomCB.setBounds(690, 124, 132, 20);
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
		btnRequest.setBounds(690, 230, 132, 23);
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
		btnRemoveBooking.setBounds(549, 498, 167, 23);
		getContentPane().add(btnRemoveBooking);
		
		myBookingsButton = new JButton("My Bookings");
		myBookingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myBookings();
			}
		});
		myBookingsButton.setBounds(364, 498, 167, 23);
		getContentPane().add(myBookingsButton);
		
		selectDayBookingsButton = new JButton("Bookings on Selected Day");
		selectDayBookingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bookingsOnSelectedDay();
			}
		});
		selectDayBookingsButton.setBounds(170, 498, 167, 23);
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
		bookingsList.setBounds(155, 295, 603, 182);
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
	
	public void initializeAdminGui() {
		this.setBounds(0,0,1300,600);
		userLabel.setBounds(1000, 13, 126, 23);
		btnLogout.setBounds(1150, 13, 89, 23);
		lblRequestBooking.setBounds(541, 69, 119, 14);
		lblActivityName.setBounds(454, 97, 97, 14);
		activityTextField.setBounds(560, 94, 132, 19);
		lblRoom.setBounds(454, 130, 46, 14);
		roomCB.setBounds(560, 124, 132, 20);
		lblTime.setBounds(454, 158, 46, 14);
		timeSpinner.setBounds(560, 155, 132, 20);
		lblLength.setBounds(454, 189, 46, 14);
		durationSpinner.setBounds(560, 186, 132, 20);
		btnRequest.setBounds(560, 230, 132, 23);
		btnRemoveBooking.setBounds(399, 498, 167, 23);
		selectDayBookingsButton.setBounds(20, 498, 167, 23);
		myBookingsButton.setBounds(214, 498, 167, 23);
		
		JLabel lblAddUser = new JLabel("Add User");
		lblAddUser.setBounds(824, 69, 79, 14);
		getContentPane().add(lblAddUser);
		
		JLabel lblAddRoom = new JLabel("Add Room");
		lblAddRoom.setBounds(1079, 69, 97, 14);
		getContentPane().add(lblAddRoom);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(858, 94, 86, 20);
		getContentPane().add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(792, 97, 46, 14);
		getContentPane().add(lblName);
		
		JButton btnApprove = new JButton("Approve Request");
		btnApprove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				approveRequest();
			}
			
		});
		btnApprove.setBounds(591, 498, 167, 23);
		getContentPane().add(btnApprove);
		
		lblUsername = new JLabel("Username");
		lblUsername.setBounds(792, 130, 67, 14);
		getContentPane().add(lblUsername);
		
		usernameTextField = new JTextField();
		usernameTextField.setBounds(858, 127, 86, 20);
		getContentPane().add(usernameTextField);
		usernameTextField.setColumns(10);
		
		passwordTextField = new JTextField();
		passwordTextField.setBounds(858, 155, 86, 20);
		getContentPane().add(passwordTextField);
		passwordTextField.setColumns(10);
		
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(792, 158, 46, 14);
		getContentPane().add(lblPassword);
		
		lblEmail = new JLabel("Email");
		lblEmail.setBounds(792, 189, 46, 14);
		getContentPane().add(lblEmail);
		
		emailTextField = new JTextField();
		emailTextField.setBounds(858, 186, 86, 20);
		getContentPane().add(emailTextField);
		emailTextField.setColumns(10);
		
		btnAddUser = new JButton("Add User");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addUser();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAddUser.setBounds(855, 230, 89, 23);
		getContentPane().add(btnAddUser);
		
		lblUserList = new JLabel("Users List");
		lblUserList.setBounds(876, 270, 79, 14);
		getContentPane().add(lblUserList);
		
		DefaultListModel<User> modelU = new DefaultListModel<>();
		usersList = new JList(modelU);
		usersList.setBounds(792, 295, 201, 182);
		getContentPane().add(usersList);
		
		JButton btnRemoveUser = new JButton("Remove User");
		btnRemoveUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					removeUser();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnRemoveUser.setBounds(843, 498, 112, 23);
		getContentPane().add(btnRemoveUser);
		
		JLabel lblRoomName = new JLabel("Room Name");
		lblRoomName.setBounds(1031, 97, 79, 14);
		getContentPane().add(lblRoomName);
		
		roomNameTextField = new JTextField();
		roomNameTextField.setBounds(1120, 94, 112, 20);
		getContentPane().add(roomNameTextField);
		roomNameTextField.setColumns(10);
		
		JButton btnAddRoom = new JButton("Add Room");
		btnAddRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					addRoom();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnAddRoom.setBounds(1120, 126, 112, 23);
		getContentPane().add(btnAddRoom);
		
		DefaultListModel<Room> modelR = new DefaultListModel<>();
		roomsList = new JList(modelR);
		roomsList.setBounds(1031, 295, 201, 182);
		getContentPane().add(roomsList);
		
		JLabel lblRoomsList = new JLabel("Rooms List");
		lblRoomsList.setBounds(1107, 270, 89, 14);
		getContentPane().add(lblRoomsList);
		
		JButton btnRemoveRoom = new JButton("Remove Room");
		btnRemoveRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					removeRoom();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnRemoveRoom.setBounds(1070, 498, 126, 23);
		getContentPane().add(btnRemoveRoom);
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
	
	public void initializeList(JList list, Collection values) {
		DefaultListModel listModel = (DefaultListModel) list.getModel();
		for(Object value: values) {
			listModel.addElement(value);
		}
	}
	
	public void setUserLabel(String userLabel) {
		this.userLabel.setText(userLabel);
	}
	
	public void submitButtonPressed() throws IOException {
		String activityName = activityTextField.getText();
		if(!activityName.isEmpty()) {
			if(calendar.getSelectedRow() != -1 && calendar.getValueAt(calendar.getSelectedRow(), calendar.getSelectedColumn()) != null) {
				int status = 0;
				if(singleDayRB.isSelected()) {
					status = Booking.singleDay;
				}
				else if(weekRB.isSelected()) {
					status = Booking.eachWeek;
				}
				else if(calendar.getSelectedRow() == 0) {
					status = Booking.entireSemester;
				}
				else {
					JOptionPane.showMessageDialog(this, "Select a day of the week to book a day for the rest of the semester");
					return;
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
	
	public void clearList(JList list) {
		DefaultListModel listModel = (DefaultListModel) list.getModel();
		listModel.removeAllElements();
	}
	
	public void myBookings() {
		clearList(bookingsList);
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
		clearList(bookingsList);
	
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
	
	public void addUser() throws IOException {
		User u;
		String name = nameTextField.getText();
		String username = usernameTextField.getText();
		String password = passwordTextField.getText();
		String email = emailTextField.getText();
		if(name != null && username != null && password != null && email != null) {
			u = new User(name,username,password,email,0,0,system);
			system.addUser(u);
			DefaultListModel listModel = (DefaultListModel) usersList.getModel();
			listModel.addElement(u);
		}
		else {
			JOptionPane.showMessageDialog(this, "All fields must be entered to add a user");
		}
	}
	
	public void removeUser() throws IOException {
		if(usersList.isSelectionEmpty()) {
			JOptionPane.showMessageDialog(this, "You must select a user first in the list to remove");
		}
		else {
			DefaultListModel listModel = (DefaultListModel) usersList.getModel();
			system.removeUser(((User) usersList.getSelectedValue()).getUserName());
			listModel.removeElement(usersList.getSelectedValue());
		}
	}
	
	public void addRoom() throws IOException {
		Room r;
		String roomId = roomNameTextField.getText();
		if(roomId != null) {
			r = new Room(roomId, 0);
			system.addRoom(r);
			DefaultListModel listModel = (DefaultListModel) roomsList.getModel();
			listModel.addElement(r);
			roomCB.addItem(r);
		}
		else {
			JOptionPane.showMessageDialog(this, "All fields must be entered to add a room");
		}
	}
	
	public void removeRoom() throws IOException {
		if(roomsList.isSelectionEmpty()) {
			JOptionPane.showMessageDialog(this, "You must select a room first in the list to remove");
		}
		else {
			roomCB.removeItem(roomsList.getSelectedValue());
			DefaultListModel listModel = (DefaultListModel) roomsList.getModel();
			system.removeRoom((Room) roomsList.getSelectedValue());
			listModel.removeElement(roomsList.getSelectedValue());
		}
	}
	
	public void approveRequest() {
		if(bookingsList.isSelectionEmpty()) {
			JOptionPane.showMessageDialog(this, "You must select a booking request first in the list to approve");
		}
		else {
			
		}
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
