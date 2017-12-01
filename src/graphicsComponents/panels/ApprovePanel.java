package graphicsComponents.panels;

import javax.swing.JPanel;

import graphicsComponents.frames.AdminFrame;
import graphicsComponents.utils.WideComboBox;
import main.SpaceSystem;
import schedule.Booking;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

import javax.swing.JComboBox;
import javax.swing.JButton;

public class ApprovePanel extends JPanel {
	
	private SpaceSystem system;
	private AdminFrame frame;
	
	WideComboBox pendingsCB;
	WideComboBox requestsCB;
	
	public ApprovePanel(SpaceSystem system, AdminFrame frame) {
		this.system = system;
		this.frame = frame;
		setLayout(null);
		initialize();
		initializeRequestsList();
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
		
		requestsCB = new WideComboBox();
		requestsCB.setBounds(10, 69, 159, 20);
		add(requestsCB);
		
		JLabel lblRequests = new JLabel("Requests");
		lblRequests.setBounds(10, 51, 93, 14);
		add(lblRequests);
		
		JButton btnViewPriorityList = new JButton("View Request Priorities");
		btnViewPriorityList.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				viewPriorityListPressed();
			}
			
		});
		btnViewPriorityList.setBounds(179, 68, 196, 23);
		add(btnViewPriorityList);
	}
	
	public void initializeRequestsList() {
		requestsCB.removeAllItems();
		Collection<LinkedList<Booking>> requestsList = system.getPendingRequests().values();
		for(LinkedList<Booking> requests: requestsList) {
			Booking topPriority = requests.peekFirst();
			requestsCB.addItem(topPriority);
		}
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
		initializeRequestsList();
	}
	
	public void viewPriorityListPressed() {
		if(requestsCB.getItemCount() == 0) {
			JOptionPane.showMessageDialog(this, "You must make a request selection to view its priorities");
			return;
		}
		else {
			Collection<LinkedList<Booking>> requestsList = system.getPendingRequests().values();
			for(LinkedList<Booking> requests: requestsList) {
				Booking topPriority = requests.peekFirst();
				if(topPriority.equals(requestsCB.getSelectedItem())) {
					initializePrioritiesList(requests);
					break;
				}
			}
		}
	}
	
	public void highlightRequestedDays() {
		
	}
}
