package graphicsComponents;

import javax.swing.JPanel;

import main.SpaceSystem;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class ApprovePanel extends JPanel {
	
	private SpaceSystem system;
	
	public ApprovePanel(SpaceSystem system) {
		this.system = system;
		setLayout(null);
		
		JLabel lblApproveBookings = new JLabel("Approve Bookings");
		lblApproveBookings.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblApproveBookings.setBounds(10, 11, 171, 29);
		add(lblApproveBookings);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 109, 304, 20);
		add(comboBox);
		
		JLabel lblPendingBookingsList = new JLabel("Pending Bookings List");
		lblPendingBookingsList.setBounds(10, 84, 136, 14);
		add(lblPendingBookingsList);
		
		JButton btnNewButton = new JButton("Approve");
		btnNewButton.setBounds(10, 140, 136, 23);
		add(btnNewButton);
	}
	
	public void initialize() {
		
	}
}
