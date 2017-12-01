package graphicsComponents.frames;

import java.awt.Component;
import java.awt.Font;
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
import javax.swing.table.DefaultTableModel;

import graphicsComponents.panels.ApprovePanel;
import graphicsComponents.panels.RequestsPanel;
import graphicsComponents.panels.RoomsPanel;
import graphicsComponents.utils.Highlighter;
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
	private WideComboBox roomCB;
	private JSpinner timeFromSpinner;
	private JSpinner timeToSpinner;
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
	private JTextField roomNameTextField;

	private JButton btnRemoveBooking;
	private JButton selectDayBookingsButton;
	private JButton myBookingsButton;
	
	private RoomsPanel roomsTab;
	private RequestsPanel requestsTab;
	private ApprovePanel approveTab;
	
	private Date date = new Date();
	private JLabel lblCalendar;
	private JLabel lblSelectRoom;
	
	

	public UserFrame(SpaceSystem system) {
		this.system = system;
		initialize();
		initializeRooms();
	}
	
	private void initialize() {
		this.setBounds(200, 50, 1061, 405);
		getContentPane().setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Space System");
		
		monthCB = new WideComboBox(Month.values());
		monthCB.setBounds(139, 103, 126, 20);
		monthCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				initializeCalendar();
				changeSemester();
			} 
		});
		getContentPane().add(monthCB);
		
		semesterCB = new WideComboBox(new String[]{"Winter", "Summer", "Fall"});
		semesterCB.setBounds(10, 103, 119, 20);
		semesterCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				initializeSemesterCalendar();
				initializeCalendar();
			} 
		});
		getContentPane().add(semesterCB);
		
		DefaultTableModel model = new DefaultTableModel();
	    model.setColumnCount(7);
	    model.setRowCount(7);
		table = new JTable();
		table.setModel(model);
		table.setBounds(10, 134, 376, 112);
		table.setCellSelectionEnabled(true);
		initializeCalendar();
		getContentPane().add(table);
	
		userLabel = new JLabel("Welcome " + null);
		userLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		userLabel.setBounds(21, 24, 172, 23);
		getContentPane().add(userLabel);
		
		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				logout();
			}
		});
		btnLogout.setBounds(224, 24, 119, 23);
		getContentPane().add(btnLogout);
		
		lblCalendar = new JLabel("Calendar");
		lblCalendar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCalendar.setBounds(10, 78, 119, 14);
		getContentPane().add(lblCalendar);
		
		roomCB = new WideComboBox();
		roomCB.setBounds(10, 300, 376, 20);
		getContentPane().add(roomCB);
		
		lblSelectRoom = new JLabel("Select Room");
		lblSelectRoom.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSelectRoom.setBounds(10, 265, 104, 14);
		getContentPane().add(lblSelectRoom);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		tabbedPane.setBounds(396, 24, 639, 328);
		getContentPane().add(tabbedPane);
		
		requestsTab = new RequestsPanel(system, this);
		tabbedPane.addTab("Request Bookings", requestsTab);
	}
	
public void initializeCalendar() {
		
		String[] dayOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
		for(int i = 0; i < table.getColumnCount(); i++) {
			table.setValueAt(dayOfWeek[i], 0, i);
		}
		Month selected = (Month) monthCB.getSelectedItem();
		int dayCount = 0;
		for(int i = 1; i < 7; i++) {
			for(int j = 0; j < 7 && dayCount < selected.getDays(); j++) {
				
				if(j < selected.getIndexStartDay() && i == 1) {
				    
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
		
		if(selected.getSemester() == 1) {
			semesterCB.setSelectedIndex(0);
		}
		else if(selected.getSemester() == 2) {
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
		
		if(semester == 0 && month.getSemester() != 1) {
			monthCB.setSelectedIndex(0);
		}
		else if(semester == 1 && month.getSemester() != 2) {
			monthCB.setSelectedIndex(4);
		}
		else if(semester == 2 && month.getSemester() != 3) {
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
	
	
	public Component returnComponent(int componentNum) {
		if(componentNum == ROOM_CB) return roomCB;
		else if(componentNum == SEMESTER_CB) return semesterCB;
		else if(componentNum == MONTH_CB) return monthCB;
		else if(componentNum == TABLE) return table;
		return null;
	}

	public void setUserLabel(String userLabel) {
		this.userLabel.setText(userLabel);
	}

	public void highlight(Highlighter h) {
		h.highlight();
	}

	@Override
	public int getTableRow(int day) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTableColumn(int day) {
		// TODO Auto-generated method stub
		return 0;
	}
}
