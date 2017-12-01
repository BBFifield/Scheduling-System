package graphicsComponents.panels;

import javax.swing.JPanel;
import javax.swing.JTable;

import graphicsComponents.frames.AdminFrame;
import graphicsComponents.frames.CommonFrame;
import graphicsComponents.utils.WideComboBox;
import main.SpaceSystem;
import schedule.Booking;
import schedule.Month;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;

import javax.swing.JComboBox;
import javax.swing.JButton;

public class ApprovePanel extends JPanel {
	
	private SpaceSystem system;
	private AdminFrame frame;
	
	private WideComboBox pendingsCB;
	private WideComboBox monthCB;
	private JTable table;
	
	public ApprovePanel(SpaceSystem system, AdminFrame frame) {
		this.system = system;
		this.frame = frame;
		setLayout(null);
		this.monthCB = (WideComboBox) frame.returnComponent(CommonFrame.MONTH_CB);
		this.table = (JTable) frame.returnComponent(CommonFrame.TABLE);
		initialize();
		
	}
	
	public void initialize() {
		JLabel lblApproveBookings = new JLabel("Approve Bookings");
		lblApproveBookings.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblApproveBookings.setBounds(10, 11, 171, 29);
		add(lblApproveBookings);
		
		pendingsCB = new WideComboBox();
		pendingsCB.setBounds(10, 136, 304, 20);
		add(pendingsCB);
		
		JLabel lblPendingBookingsList = new JLabel("Request Priorities");
		lblPendingBookingsList.setBounds(10, 111, 136, 14);
		add(lblPendingBookingsList);
		
		JButton approveButton = new JButton("Approve");
		approveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					approveRequestPressed();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		approveButton.setBounds(10, 167, 136, 23);
		add(approveButton);
		
		JButton btnViewPriorityList = new JButton("View Request Priorities");
		btnViewPriorityList.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				viewPriorityListPressed();
			}
			
		});
		btnViewPriorityList.setBounds(10, 67, 196, 23);
		add(btnViewPriorityList);
	}
	
	public void initializePrioritiesList(Collection<Booking> pendings) {
		pendingsCB.removeAllItems();
		for(Booking b: pendings) {
			pendingsCB.addItem(b);
		}
	}
	
	public void approveRequestPressed() throws IOException {
		if(pendingsCB.getItemCount() == 0) {
			JOptionPane.showMessageDialog(this, "You must first select a pending request to approve");
			return;
		}
		Booking request = (Booking) pendingsCB.getSelectedItem();
		request.approveBooking();
		system.addBooking(request);
		pendingsCB.removeAllItems();
		system.removePendingRequest(request.getActivityID());
		frame.highlightRequests();
	}
		
	public void viewPriorityListPressed() {
		
		if(table.getSelectedRow() != -1) {
			
			int dayHighlighted = (int) table.getValueAt(table.getSelectedRow(), table.getSelectedColumn());
			System.out.println(dayHighlighted);
			Collection<LinkedList<Booking>> pendingRequests = system.getPendingRequests().values();
			boolean clickedRequests = false;
			Month month = (Month) monthCB.getSelectedItem();
			for(LinkedList<Booking> r: pendingRequests) {
				Calendar requestday = r.getLast().getDate();
				int dayRequest = requestday.get(Calendar.DAY_OF_MONTH);
				System.out.println(dayRequest);
				if(dayRequest == dayHighlighted) {
					initializePrioritiesList(r);
					clickedRequests = true;
				}
			}
			if(clickedRequests == false) {
				JOptionPane.showMessageDialog(this, "Must click on highlighted day to see priority list");
			}
		}
	}
	
	public void highlightRequestedDays() {
		
	}
}
