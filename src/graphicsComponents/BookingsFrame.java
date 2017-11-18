package graphicsComponents;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Dialog.ModalExclusionType;
import java.awt.GridLayout;
import javax.swing.JCheckBox;

public class BookingsFrame {
	JFrame frame;

	public BookingsFrame() {
		frame = new JFrame();
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(500, 500));
		frame.add(panel);
		frame.setAlwaysOnTop(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(400, 400));
		frame.setVisible(true);
		frame.dispose();
	}
}
