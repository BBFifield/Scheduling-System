package graphicsComponents;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.util.Date;
import java.util.Enumeration;
import java.util.ArrayList;
import java.util.Arrays;
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
import javax.swing.JCheckBox;
 
public class AdminGUITester extends JFrame {
	
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
	private JTextField nameTextField;
	private JLabel lblRequestBooking;
	private JLabel lblActivityName;
	private JLabel lblRoom;
	private JLabel lblTimeFrom;
	private JLabel lblTimeTo;
	private JButton btnRequest;
	private JLabel lblUsername;
	private JTextField usernameTextField;
	private JTextField passwordTextField;
	private JLabel lblPassword;
	private JLabel lblEmail;
	private JTextField emailTextField;
	private JButton btnAddUser;
	private JLabel lblUserList;
	private WideComboBox usersCB;
	private JTextField roomNameTextField;

	private JButton btnRemoveBooking;
	private JButton selectDayBookingsButton;
	private JButton myBookingsButton;
	
	ArrayList<JCheckBox> dayCheckBoxes = new ArrayList<>();
	
	private Date date = new Date();

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
	public AdminGUITester(SpaceSystem system) {
		this.system = system;
		initialize();
		//if(system.searchUser(UserValidator.userLoggedIn).getPermissions() == User.adminPermissions) {
			initializeAdminGui();
		}
		//initializeRooms();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//this.setBounds(400, 50, 867, 618);
		getContentPane().setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Space System");
	
		userLabel = new JLabel("Welcome " + null);
		//userLabel.setBounds(555, 13, 126, 23);
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
		timeFromSpinner.setBounds(712, 79, 46, 20);
		JSpinner.DateEditor de = new JSpinner.DateEditor(timeFromSpinner, "HH");
		timeFromSpinner.setEditor(de);
		getContentPane().add(timeFromSpinner);
		
		timeToSpinner = new JSpinner(new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY));
		timeToSpinner.setBounds(778, 79, 46, 20);
		JSpinner.DateEditor ne1 = new JSpinner.DateEditor(timeToSpinner, "HH");
		timeToSpinner.setEditor(ne1);
		getContentPane().add(timeToSpinner);
		
		JLabel lblNewLabel_1 = new JLabel("Request and Remove Bookings");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 11, 232, 23);
		getContentPane().add(lblNewLabel_1);
		
		lblRequestBooking = new JLabel("Request Booking");
		//lblRequestBooking.setBounds(509, 133, 119, 14);
		getContentPane().add(lblRequestBooking);
		
		lblTimeTo = new JLabel("Time To");
		//lblTimeTo.setBounds(684, 54, 46, 14);
		getContentPane().add(lblTimeTo);
		
		lblTimeFrom = new JLabel("Time From");
		//lblTimeFrom.setBounds(595, 54, 76, 14); 
		getContentPane().add(lblTimeFrom);
		
		activityTextField = new JTextField();
		//activityTextField.setBounds(555, 164, 136, 19);
		getContentPane().add(activityTextField);
		activityTextField.setColumns(10);
		
		lblActivityName = new JLabel("Activity Name");
		//lblActivityName.setBounds(448, 167, 97, 14);
		getContentPane().add(lblActivityName);
		
		lblRoom = new JLabel("Select Room ");
		//lblRoom.setBounds(483, 54, 106, 14);
		getContentPane().add(lblRoom);
		
		roomCB = new WideComboBox();
		//roomCB.setBounds(442, 79, 136, 20);
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
		//btnRequest.setBounds(555, 194, 136, 23);
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
		//btnRemoveBooking.setBounds(399, 515, 167, 23);
		getContentPane().add(btnRemoveBooking);
		
		myBookingsButton = new JButton("My Bookings");
		myBookingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myBookings();
			}
		});
		//myBookingsButton.setBounds(214, 515, 167, 23);
		getContentPane().add(myBookingsButton);
		
		selectDayBookingsButton = new JButton("Bookings on Selected Day");
		selectDayBookingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bookingsOnSelectedDay();
			}
		});
		//selectDayBookingsButton.setBounds(20, 515, 167, 23);
		getContentPane().add(selectDayBookingsButton);
		
		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				logout();
			}
		});
		//btnLogout.setBounds(733, 13, 89, 23);
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
		lblSemester.setBounds(20, 54, 97, 14);
		getContentPane().add(lblSemester);
	}
	
	public void initializeAdminGui() {
		this.setBounds(0,0,1111,600);
		userLabel.setBounds(785, 13, 126, 23);
		btnLogout.setBounds(921, 13, 89, 23);
		lblRequestBooking.setBounds(528, 178, 119, 14);
		lblActivityName.setBounds(462, 208, 97, 14);
		activityTextField.setBounds(559, 203, 148, 19);
		lblRoom.setBounds(491, 54, 95, 14);
		roomCB.setBounds(462, 79, 112, 20);
		lblTimeFrom.setBounds(711, 54, 67, 14);
		lblTimeTo.setBounds(778, 54, 46, 14);
		btnRequest.setBounds(559, 230, 148, 23);
		btnRemoveBooking.setBounds(399, 515, 167, 23);
		selectDayBookingsButton.setBounds(20, 515, 167, 23);
		myBookingsButton.setBounds(214, 515, 167, 23);
		
		JLabel lblAddUser = new JLabel("Add User");
		lblAddUser.setBounds(892, 69, 79, 14);
		getContentPane().add(lblAddUser);
		
		JLabel lblAddRoom = new JLabel("Add Room");
		lblAddRoom.setBounds(892, 286, 97, 14);
		getContentPane().add(lblAddRoom);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(924, 94, 86, 20);
		getContentPane().add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(857, 97, 46, 14);
		getContentPane().add(lblName);
		
		JButton btnApprove = new JButton("Approve Request");
		btnApprove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				approveRequest();
			}
			
		});
		btnApprove.setBounds(591, 515, 167, 23);
		getContentPane().add(btnApprove);
		
		lblUsername = new JLabel("Username");
		lblUsername.setBounds(858, 128, 67, 14);
		getContentPane().add(lblUsername);
		
		usernameTextField = new JTextField();
		usernameTextField.setBounds(924, 125, 86, 20);
		getContentPane().add(usernameTextField);
		usernameTextField.setColumns(10);
		
		passwordTextField = new JTextField();
		passwordTextField.setBounds(924, 160, 86, 20);
		getContentPane().add(passwordTextField);
		passwordTextField.setColumns(10);
		
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(858, 163, 67, 14);
		getContentPane().add(lblPassword);
		
		lblEmail = new JLabel("Email");
		lblEmail.setBounds(857, 191, 46, 14);
		getContentPane().add(lblEmail);
		
		emailTextField = new JTextField();
		emailTextField.setBounds(924, 188, 86, 20);
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
		btnAddUser.setBounds(921, 218, 89, 23);
		getContentPane().add(btnAddUser);
		
		lblUserList = new JLabel("Users List");
		lblUserList.setBounds(491, 110, 79, 14);
		getContentPane().add(lblUserList);
		
		DefaultListModel<User> modelU = new DefaultListModel<>();
		
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
		btnRemoveUser.setBounds(591, 129, 112, 23);
		getContentPane().add(btnRemoveUser);
		
		JLabel lblRoomName = new JLabel("Room Name");
		lblRoomName.setBounds(858, 323, 97, 14);
		getContentPane().add(lblRoomName);
		
		roomNameTextField = new JTextField();
		roomNameTextField.setBounds(939, 320, 112, 20);
		getContentPane().add(roomNameTextField);
		roomNameTextField.setColumns(10);
		
		JButton btnAddRoom = new JButton("Set Room");
		btnAddRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					addRoom();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnAddRoom.setBounds(939, 411, 112, 23);
		getContentPane().add(btnAddRoom);
		
		DefaultListModel<Room> modelR = new DefaultListModel<>();
		
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
		btnRemoveRoom.setBounds(584, 78, 114, 23);
		getContentPane().add(btnRemoveRoom);
		
		JCheckBox chckbxMon = new JCheckBox("Mon");
		chckbxMon.setBounds(857, 354, 57, 23);
		dayCheckBoxes.add(chckbxMon);
		getContentPane().add(chckbxMon);
		
		JCheckBox chckbxTue = new JCheckBox("Tue");
		chckbxTue.setBounds(924, 354, 57, 23);
		dayCheckBoxes.add(chckbxTue);
		getContentPane().add(chckbxTue);
		
		JCheckBox chckbxWed = new JCheckBox("Wed");
		chckbxWed.setBounds(983, 354, 57, 23);
		dayCheckBoxes.add(chckbxWed);
		getContentPane().add(chckbxWed);
		
		JCheckBox chckbxThur = new JCheckBox("Thur");
		chckbxThur.setBounds(857, 380, 57, 23);
		dayCheckBoxes.add(chckbxThur);
		getContentPane().add(chckbxThur);
		
		JCheckBox chckbxFri = new JCheckBox("Fri");
		chckbxFri.setBounds(924, 380, 46, 23);
		dayCheckBoxes.add(chckbxFri);
		getContentPane().add(chckbxFri);
		
		JCheckBox chckbxSat = new JCheckBox("Sat");
		chckbxSat.setBounds(983, 380, 46, 23);
		dayCheckBoxes.add(chckbxSat);
		getContentPane().add(chckbxSat);
		
		JCheckBox chckbxSun = new JCheckBox("Sun");
		chckbxSun.setBounds(857, 411, 57, 23);
		dayCheckBoxes.add(chckbxSun);
		getContentPane().add(chckbxSun);
		
		usersCB = new WideComboBox();
		usersCB.setBounds(462, 132, 112, 20);
		getContentPane().add(usersCB);
	}
	
	public void initializeCalendar() {
		
		String[] dayOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
		for(int i = 0; i < table.getColumnCount(); i++) {
			table.setValueAt(dayOfWeek[i], 0, i);
		}
		Month selected = (Month) monthCB.getSelectedItem();
		int dayCount = 0;
		for(int i = 1; i < 7; i++) {
			for(int j = 0; j < 7 && dayCount < selected.days; j++) {
				
				if(j < selected.indexStartDay && i == 1) {
				    
					table.setValueAt(null, 1, j);
				}
				else if(i == 1) {
					dayCount++;
					table.setValueAt(dayCount ,1, j);
				}
				else {
					dayCount++;
					table.setValueAt(dayCount ,i, j);
				}
			}
		}
	}
	
	public void changeSemester() {
		
		Month selected = (Month) monthCB.getSelectedItem();
		
		if(selected.semester == 1) {
			semesterCB.setSelectedIndex(0);
		}
		else if(selected.semester == 2) {
			semesterCB.setSelectedIndex(1);
		}
		else {
			semesterCB.setSelectedIndex(2);
		}
		
		initializeRooms();
	}
	
	public void initializeSemesterCalendar() {
		int semester = semesterCB.getSelectedIndex();
		Month month = (Month) monthCB.getSelectedItem();
		
		if(semester == 0 && month.semester != 1) {
			monthCB.setSelectedIndex(0);
		}
		else if(semester == 1 && month.semester != 2) {
			monthCB.setSelectedIndex(4);
		}
		else if(semester == 2 && month.semester != 3) {
			monthCB.setSelectedIndex(8);
		}
		
		initializeRooms();
	}
	
	public void initializeRooms() {
		roomCB.removeAllItems();
		Collection<Room> rooms = system.getRooms().values();
		for(Room r: rooms) {
			if(r.getSemester() == semesterCB.getSelectedIndex()) {
				roomCB.addItem(r);
			}
		}
	}
	
	/*
	public void initializeList(JList list, Collection values) {
		for(Object value: values) {
			listModel.addElement(value);
		}
	}
	*/
	
	public void setUserLabel(String userLabel) {
		this.userLabel.setText(userLabel);
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
				
				String roomName = ((Room) roomCB.getSelectedItem()).getRoomId();
				Date spinnerDateFrom = (Date) timeFromSpinner.getValue();
				Calendar calendarInstanceFrom = Calendar.getInstance();
				
				calendarInstanceFrom.setTime(spinnerDateFrom);
				int monthIndex = ((Month) monthCB.getSelectedItem()).monthIndex;
				int weekIndex = table.getSelectedRow();
				calendarInstanceFrom.set(Calendar.DAY_OF_WEEK, weekIndex);
				
				Date spinnerDateTo = (Date) timeToSpinner.getValue();
				Calendar calendarInstanceTo = Calendar.getInstance();
				calendarInstanceTo.setTime(spinnerDateTo);
				
				int duration;
				if(calendarInstanceTo.get(Calendar.HOUR_OF_DAY) > calendarInstanceFrom.get(Calendar.HOUR_OF_DAY)) {
					duration = calendarInstanceTo.get(Calendar.HOUR_OF_DAY) - calendarInstanceFrom.get(Calendar.HOUR_OF_DAY);
				}
				else {
					JOptionPane.showMessageDialog(this, "Start time of event must start earlier than end time.");
					return;
				}
				if(table.getSelectedRow() == 0) {
					String semester = (String) semesterCB.getSelectedItem();
					system.addBooking(new Booking(activityName,system.searchUser(UserValidator.userLoggedIn), system.searchRoom(roomName), duration, calendarInstanceFrom, semester));
				}
				else {
					int day = ((int) table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
					calendarInstanceFrom.set(Calendar.DAY_OF_MONTH, day);
					calendarInstanceFrom.set(Calendar.MONTH, monthIndex);
					system.addBooking(new Booking(activityName,system.searchUser(UserValidator.userLoggedIn), system.searchRoom(roomName), duration, status, calendarInstanceFrom));
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
			usersCB.addItem(u);
		}
		else {
			JOptionPane.showMessageDialog(this, "All fields must be entered to add a user");
		}
	}
	
	public void removeUser() throws IOException {
		system.removeUser(((User) usersCB.getSelectedItem()).getUserName());
		usersCB.removeItem(usersCB.getSelectedItem());
	}
	
	
	public void addRoom() throws IOException {
		Room r;
		String roomId = roomNameTextField.getText();
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		
		c1.setTime((Date) timeFromSpinner.getValue());
		int timeFrom = c1.get(Calendar.HOUR_OF_DAY);
		
		c2.setTime((Date) timeToSpinner.getValue());
		int timeTo = c2.get(Calendar.HOUR_OF_DAY);
		
		ArrayList<Calendar> days = new ArrayList<>();
		
		for(int i = 0; i < dayCheckBoxes.size(); i++) {
			if(dayCheckBoxes.get(i).isSelected()) {
				Calendar calendarInstance = Calendar.getInstance();
				calendarInstance.set(calendarInstance.DAY_OF_WEEK, i+1);
				days.add(calendarInstance);
			}
		}
	
		if(roomId != null && timeFrom < timeTo && days.size() > 0) {
			if(system.getRooms().get(roomId) != null) {
				if(system.getRooms().get(roomId).getTimeFrom() == timeFrom && system.getRooms().get(roomId).getTimeTo() == timeTo) {
					r = system.getRooms().get(roomId);
					r.merge(days);
					initializeRooms();
				}
				else {
					JOptionPane.showMessageDialog(this, "The addition of days requires that room times be equivalent to those on record");
					return;
				}
			}
			else {
				r = new Room(roomId, timeFrom, timeTo, days, semesterCB.getSelectedIndex());
				system.addRoom(r);
				roomCB.addItem(r);
			}
		}
		else {
			JOptionPane.showMessageDialog(this, "All fields must be entered to add a room. Remember that the starting hours needs to be less than the ending hours.");
		}
	}
	
	public void removeRoom() throws IOException {
		system.removeRoom((Room) roomCB.getSelectedItem());
		roomCB.removeItem(roomCB.getSelectedItem());
	}
	
	public void approveRequest() {
		if(bookingsList.isSelectionEmpty()) {
			JOptionPane.showMessageDialog(this, "You must select a booking request first in the list to approve");
		}
		else {
			
		}
	}

