package graphicsComponents;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import schedule.Booking;
import schedule.Month;
import schedule.Room;
import java.awt.Font;

public class RoomsPanel extends JPanel {
	
	private JSpinner timeFromSpinner;
	private JSpinner timeToSpinner;
	private WideComboBox roomCB;
	private WideComboBox semesterCB;
	private WideComboBox monthCB;
	private JTable table;
	private SpaceSystem system;

	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblTimeFrom;
	private JLabel lblTimeTo;
	private JTextField roomNameTextField;
	
	ArrayList<JCheckBox> dayCheckBoxes = new ArrayList<>();
	AdminFrame frame;
	
	private Date date = new Date();


	public RoomsPanel(SpaceSystem system, AdminFrame frame) {
		this.system = system;
		this.frame = frame;
		this.roomCB = (WideComboBox) frame.returnComponent(0);
		this.semesterCB = (WideComboBox) frame.returnComponent(1);
		this.monthCB = (WideComboBox) frame.returnComponent(2);
		this.table = (JTable) frame.returnComponent(3);
		initialize();
	}
	
	public void initialize() {
		setLayout(null);
		this.setPreferredSize(new Dimension(700,400));
		
		timeFromSpinner = new JSpinner(new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY));
		timeFromSpinner.setBounds(265,153,55,20);
		JSpinner.DateEditor de = new JSpinner.DateEditor(timeFromSpinner, "HH");
		timeFromSpinner.setEditor(de);
		this.add(timeFromSpinner);
		
		timeToSpinner = new JSpinner(new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY));
		timeToSpinner.setBounds(341, 153, 55,20);
		JSpinner.DateEditor ne1 = new JSpinner.DateEditor(timeToSpinner, "HH");
		timeToSpinner.setEditor(ne1);
		this.add(timeToSpinner);
		
		lblTimeTo = new JLabel("Time To");
		lblTimeTo.setBounds(341, 128, 55, 14);
		this.add(lblTimeTo);
		
		lblTimeFrom = new JLabel("Time From");
		lblTimeFrom.setBounds(265, 128, 72, 14);
		this.add(lblTimeFrom);
		
		JLabel lblRoomName = new JLabel("Room Name");
		lblRoomName.setBounds(33, 156, 86, 14);
		this.add(lblRoomName);
		
		roomNameTextField = new JTextField();
		roomNameTextField.setBounds(122, 153, 119, 20);
		this.add(roomNameTextField);
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
		btnAddRoom.setBounds(33, 221, 119, 23);
		this.add(btnAddRoom);
		
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
		btnRemoveRoom.setBounds(33, 69, 135, 23);
		this.add(btnRemoveRoom);
		
		JCheckBox chckbxMon = new JCheckBox("Mon");
		chckbxMon.setBounds(33, 191, 55, 23);
		dayCheckBoxes.add(chckbxMon);
		this.add(chckbxMon);
		
		JCheckBox chckbxTue = new JCheckBox("Tue");
		chckbxTue.setBounds(90, 191, 55, 23);
		dayCheckBoxes.add(chckbxTue);
		this.add(chckbxTue);
		
		JCheckBox chckbxWed = new JCheckBox("Wed");
		chckbxWed.setBounds(147, 191, 55, 23);
		dayCheckBoxes.add(chckbxWed);
		this.add(chckbxWed);
		
		JCheckBox chckbxThur = new JCheckBox("Thur");
		chckbxThur.setBounds(204, 191, 55, 23);
		dayCheckBoxes.add(chckbxThur);
		this.add(chckbxThur);
		
		JCheckBox chckbxFri = new JCheckBox("Fri");
		chckbxFri.setBounds(265, 191, 55, 23);
		dayCheckBoxes.add(chckbxFri);
		this.add(chckbxFri);
		
		JCheckBox chckbxSat = new JCheckBox("Sat");
		chckbxSat.setBounds(321, 191, 55, 23);
		dayCheckBoxes.add(chckbxSat);
		this.add(chckbxSat);
		
		JCheckBox chckbxSun = new JCheckBox("Sun");
		chckbxSun.setBounds(378, 191, 55, 23);
		dayCheckBoxes.add(chckbxSun);
		this.add(chckbxSun);
		
		JLabel lblRemoveRoom = new JLabel("Remove Room");
		lblRemoveRoom.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblRemoveRoom.setBounds(33, 44, 149, 14);
		add(lblRemoveRoom);
		
		JLabel lblAddRoom = new JLabel("Add Room");
		lblAddRoom.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblAddRoom.setBounds(33, 114, 119, 14);
		add(lblAddRoom);
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
					frame.initializeRooms();
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
	
	
}



