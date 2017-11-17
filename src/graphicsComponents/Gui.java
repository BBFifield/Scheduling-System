package graphicsComponents;
import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;

import java.util.Date;
import java.util.Calendar;
import java.awt.Font;
import javax.swing.JCheckBox;

import main.GuiSystemManager;
import main.SpaceSystem;
import schedule.Booking;

public class Gui {

	private JFrame frame;
	private JTable table;
	private JTextField textField;
	private String[] rooms = {"Gym", "Library", "EN1052", "Computer Lab"};
	private SpaceSystem system;
	private String userName;
	
	public enum Month{
		JANUARY(0, 31, 0), 
		FEBRUARY(1, 28, 4), 
		MARCH(2, 31, 4), 
		APRIL(3, 30, 6), 
		MAY(4, 31, 1), 
		JUNE(5, 30, 4), 
		JULY(6, 31, 6), 
		AUGUST(7, 31, 2), 
		SEPTEMBER(8, 30, 5), 
		OCTOBER(9, 31, 0), 
		NOVEMBER(10, 30, 3), 
		DECEMBER(11, 31, 5);
		
		private final int monthIndex;
		private final int days;
		private final int indexStartDay;
		
		Month(int monthIndex, int days, int indexStartDay) {
			this.monthIndex = monthIndex;
			this.days = days;
			this.indexStartDay = indexStartDay;
		}
	}

	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 522, 465);
		frame.setTitle("Space Schedule");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel userLabel = new JLabel("Welcome " + userName);
		userLabel.setBounds(250, 11, 232, 23);
		frame.getContentPane().add(userLabel);
		
		JLabel lblNewLabel = new JLabel("Scheduled Bookings");
		lblNewLabel.setBounds(10, 45, 126, 14);
		frame.getContentPane().add(lblNewLabel);
		
		
		table = new JTable(7, 7);
		table.setBounds(10, 97, 402, 112);
		initializeCalendar(table);
		frame.getContentPane().add(table);
		
		
		JComboBox comboBox = new JComboBox(Month.values());
		comboBox.setBounds(10, 66, 126, 20);
		frame.getContentPane().add(comboBox);
		
		Date date = new Date();
		JSpinner spinner = new JSpinner(new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY));
		spinner.setBounds(92, 310, 132, 20);
		JSpinner.DateEditor de = new JSpinner.DateEditor(spinner, "HH:mm:ss");
		spinner.setEditor(de);
		frame.getContentPane().add(spinner);
		
		JSpinner jSpinner1 = new JSpinner(new SpinnerNumberModel(1, 1, 3, 1));
		jSpinner1.setBounds(92, 341, 132, 20);
		JSpinner.NumberEditor ne1 = new JSpinner.NumberEditor(jSpinner1);
		jSpinner1.setEditor(ne1);
		frame.getContentPane().add(jSpinner1);
		
		JLabel lblNewLabel_1 = new JLabel("Request or Remove Booking");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 11, 232, 23);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Remove");
		lblNewLabel_2.setBounds(335, 220, 77, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Request");
		lblNewLabel_3.setBounds(128, 220, 77, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("From");
		lblNewLabel_4.setBounds(10, 344, 46, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("To");
		lblNewLabel_5.setBounds(10, 313, 46, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		textField = new JTextField();
		textField.setBounds(92, 251, 132, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblActivityName = new JLabel("Activity Name");
		lblActivityName.setBounds(10, 254, 97, 14);
		frame.getContentPane().add(lblActivityName);
		
		JLabel lblNewLabel_6 = new JLabel("Room ");
		lblNewLabel_6.setBounds(10, 279, 46, 14);
		frame.getContentPane().add(lblNewLabel_6);
		
		JComboBox comboBox_1 = new JComboBox(rooms);
		comboBox_1.setBounds(92, 279, 132, 20);
		frame.getContentPane().add(comboBox_1);
		
		JButton btnNewButton = new JButton("Submit Request");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String activityName = textField.getText();
				if(!activityName.isEmpty()) {
					
					String roomName = (String) comboBox_1.getSelectedItem();
					Date spinnerDate = (Date) spinner.getValue();
					Calendar date = Calendar.getInstance();
					date.setTime(spinnerDate);
					int day = ((int) table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
					int monthIndex = ((Month) comboBox.getSelectedItem()).monthIndex;
					date.set(Calendar.DAY_OF_MONTH, day);
					date.set(Calendar.MONTH, monthIndex);
					int duration = (Integer) jSpinner1.getValue();
					
					system.addBooking(new Booking(system.searchUser(userName), system.searchRoom(roomName), duration, date));
					System.out.println(system.getBookings().get(0).getDate());
				}
			
				
			}
		});
		btnNewButton.setBounds(92, 378, 132, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnRemoveBooking = new JButton("Remove Booking");
		btnRemoveBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
			}
		});
		btnRemoveBooking.setBounds(258, 250, 222, 23);
		frame.getContentPane().add(btnRemoveBooking);
		
	}
	
	public void addSystem(SpaceSystem system) {
		this.system = system;
	}
	
	public void initializeCalendar(JTable calendar) {
		String[] dayOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
		for(int i = 0; i < table.getColumnCount(); i++) {
			calendar.setValueAt(dayOfWeek[i], 0, i);
		}
		
		int dayCount = 0;
		for(int i = 1; i < 7; i++) {
			for(int j = Month.JANUARY.indexStartDay; j < 7 && dayCount < Month.JANUARY.days; j++) {
				dayCount++;
				calendar.setValueAt(dayCount ,i, j);
			}
		}
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		SpaceSystem system = new SpaceSystem();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.addSystem(system);
					system.addGui(window);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
