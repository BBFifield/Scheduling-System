package graphicsComponents;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;

public class BookingsFrame {
	JFrame frame;
	
	public BookingsFrame() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(400,400));
		
		JButton btnNewButton = new JButton("New button");
		frame.getContentPane().add(btnNewButton, BorderLayout.NORTH);
		frame.setVisible(true);
	}
}
