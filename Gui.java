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
import java.util.Date;
import java.util.Calendar;
import java.awt.Font;
import javax.swing.JCheckBox;

public class Gui {

	private JFrame frame;
	private JTable table;
	private JTextField textField;
	private String[] rooms = {"Gym", "Library", "EN1052", "Computer Lab"};
	
	public enum Month{
		JANUARY(31, 0), 
		FEBRUARY(28, 4), 
		MARCH(31, 4), 
		APRIL(30, 6), 
		MAY(31, 1), 
		JUNE(30, 4), 
		JULY(31, 6), 
		AUGUST(31, 2), 
		SEPTEMBER(30, 5), 
		OCTOBER(31, 0), 
		NOVEMBER(30, 3), 
		DECEMBER(31, 5);
		
		private final int days;
		private final int indexStartDay;
		
		Month(int days, int indexStartDay) {
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
		
		JLabel lblNewLabel = new JLabel("Scheduled Bookings");
		lblNewLabel.setBounds(10, 45, 126, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Submit Request");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(92, 378, 132, 23);
		frame.getContentPane().add(btnNewButton);
		
		table = new JTable(7, 7);
		table.setBounds(10, 97, 402, 112);
		initializeCalendar(table);
		frame.getContentPane().add(table);
		
		JButton btnRemoveBooking = new JButton("Remove Booking");
		btnRemoveBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnRemoveBooking.setBounds(258, 250, 222, 23);
		frame.getContentPane().add(btnRemoveBooking);
		
		JComboBox comboBox = new JComboBox(Month.values());
		comboBox.setBounds(10, 66, 126, 20);
		frame.getContentPane().add(comboBox);
		
		Date date = new Date();
		JSpinner spinner = new JSpinner(new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY));
		spinner.setBounds(92, 310, 132, 20);
		JSpinner.DateEditor de = new JSpinner.DateEditor(spinner, "HH:mm:ss");
		spinner.setEditor(de);
		frame.getContentPane().add(spinner);
		
		JSpinner jSpinner1 = new JSpinner(new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY));
		jSpinner1.setBounds(92, 341, 132, 20);
		JSpinner.DateEditor de1 = new JSpinner.DateEditor(jSpinner1, "HH:mm:ss");
		jSpinner1.setEditor(de1);
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
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
